using System;
using System.Collections;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;

namespace UserList
{
    public partial class ApplicantForm : System.Web.UI.Page
    {
        static string loginid;
        static string userid;
        static DateTime? dateplaced;
        static TimeSpan activetime;

        SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\Coffee.mdf;Integrated Security=True;Connect Timeout=30");
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Access"].ToString() != "Applicant")
            {
                Response.Redirect("index.aspx");
            }
            else
            {
                comments.Attributes.Add("maxlength", "100");
                if (!IsPostBack)
                {                
                try
                    {
                        con.Open();
                        loginid = Request.QueryString["loginid"].ToString();                        
                                                
                        SqlCommand command = new SqlCommand("SELECT * from Users WHERE LoginID='" + loginid + "'", con);
                        SqlDataReader reader = null;
                        reader = command.ExecuteReader();                        

                        while (reader.Read())
                        {
                            if (reader["FirstName"].ToString() != "") txtFirstName.Text = reader["FirstName"].ToString();
                            if (reader["MiddleName"].ToString() != "") txtMiddleName.Text = reader["MiddleName"].ToString();
                            if (reader["LastName"].ToString() != "") txtLastName.Text = reader["LastName"].ToString();
                            if (reader["Suffix"].ToString() != "") txtSuffix.Text = reader["Suffix"].ToString();                            
                            if (reader["Active"].ToString() == "Yes")
                            {
                                btnActive.Text = "On";
                                btnActive.ForeColor = System.Drawing.Color.Red;
                            }
                            if (reader["Active"].ToString() == "No")
                            {
                                btnActive.Text = "Off";
                                btnActive.ForeColor = System.Drawing.Color.Gray;
                            }
                            //if (reader["ActiveTime"].ToString() != "") txtActiveTime.Text = reader["ActiveTime"].ToString();
                            if (reader["DatePlaced"].ToString() != "")
                            {
                                txtDatePlaced.Text = reader["DatePlaced"].ToString();
                                activetime = DateTime.Now - Convert.ToDateTime(txtDatePlaced.Text);
                                txtActiveTime.Text = activetime.Days.ToString();
                            }
                            if (reader["CurrentLoc"].ToString() != "") ddlCurrentLocation.SelectedValue = reader["CurrentLoc"].ToString();
                            if (reader["CurrentArea"].ToString() != "") ddlCurrentPractice.SelectedValue = reader["CurrentArea"].ToString();
                            if (reader["DesiredLoc"].ToString() != "") ddlDesiredLocation.SelectedValue = reader["DesiredLoc"].ToString();
                            if (reader["DesiredArea"].ToString() != "") ddlDesiredPractice.SelectedValue = reader["DesiredArea"].ToString();
                            if (reader["PracticeYears"].ToString() != "") ddlPracticeYears.SelectedValue = reader["PracticeYears"].ToString();
                            if (reader["PracticeMonths"].ToString() != "") ddlPracticeMonths.SelectedValue = reader["PracticeMonths"].ToString();
                            if (reader["Comments"].ToString() != "") comments.Text = reader["Comments"].ToString();
                            userid = reader["UserID"].ToString();                            
                        }                        
                        con.Close();
                    }
                    catch (Exception ex)
                    {
                        lblError.Text = "A database error has occurred. " +
                            "Message: " + ex.Message;
                        lblError.Visible = true;
                    }
                    
                }
                lbluserid.Text = userid;
            }
        }

        protected void btnSkill_Click(object sender, EventArgs e)
        {            
            var parameters = SqlDataSource1.InsertParameters;

            parameters["UserID"].DefaultValue = lbluserid.Text;
            parameters["SkillName"].DefaultValue = ddlSkills.Text;
            parameters["SkillYears"].DefaultValue = ddlSkillsYears.Text;
            parameters["SkillMonths"].DefaultValue = ddlSkillsMonths.Text;           

            try
            {                
                SqlDataSource1.Insert();
            }
            catch (Exception ex)
            {
                lblError.Text = "A database error has occurred. " +
                    "message: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void btnClose_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }

        protected void btnActive_Click(object sender, EventArgs e)
        {
            if (IsValid)
            {
                string query = "";

                if (btnActive.Text == "On")
                {
                    btnActive.Text = "Off";
                    dateplaced = null;
                    txtDatePlaced.Text = "";
                    txtActiveTime.Text = "";
                    query = "UPDATE [Users] SET Active='No' WHERE LoginID='" + loginid + "'";
                    btnActive.ForeColor = System.Drawing.Color.Gray;
                }
                else if (btnActive.Text == "Off")
                {
                    btnActive.Text = "On";
                    dateplaced = DateTime.Now;
                    txtDatePlaced.Text = dateplaced.Value.ToString("MM/dd/yyyy");
                    activetime = DateTime.Now - dateplaced.Value;
                    txtActiveTime.Text = activetime.Days.ToString();
                    query = "UPDATE [Users] SET Active='Yes' WHERE LoginID='" + loginid + "'";
                    btnActive.ForeColor = System.Drawing.Color.Red;
                }

                try
                {
                    con.Open();
                    using (SqlCommand command = new SqlCommand(query, con))
                    {
                        command.ExecuteNonQuery();
                        con.Close();                        
                    }
                }
                catch (Exception ex)
                {
                    lblError.Text = "A database error has occurred. " +
                        "message: " + ex.Message;
                    lblError.Visible = true;
                }
            }            
        }
                
        protected void btnSave_Click(object sender, EventArgs e)
        {
            if (btnActive.Text == "On")
            {
                activetime = DateTime.Now - Convert.ToDateTime(txtDatePlaced.Text);
                txtActiveTime.Text = activetime.Days.ToString();
            }
            try
            {
                string query = "UPDATE [Users] SET FirstName='"+txtFirstName.Text+"',MiddleName='"+txtMiddleName.Text+"',LastName='"+txtLastName.Text+"'," +
                    "Suffix='"+txtSuffix.Text+"',ActiveTime='"+txtActiveTime.Text+"',DatePlaced='"+txtDatePlaced.Text+"'," +
                    "CurrentLoc='"+ddlCurrentLocation.SelectedValue+"',CurrentArea='"+ddlCurrentPractice.SelectedValue+"'," +
                    "DesiredLoc='"+ddlDesiredLocation.SelectedValue+"',DesiredArea='"+ddlDesiredPractice.SelectedValue+"'," +
                    "Comments='"+comments.Text+"',PracticeYears='"+ddlPracticeYears.SelectedValue+"',PracticeMonths='"+ddlPracticeMonths.SelectedValue+"' WHERE LoginID='"+loginid+"'";

                con.Open();
                using (SqlCommand command = new SqlCommand(query, con))
                {
                    command.ExecuteNonQuery();
                    con.Close();           
                }
                lblError.Text = "Record has been saved.";
                lblError.Visible = true;
            }
            catch (Exception ex)
            {
                lblError.Text = "A database error has occurred. " +
                    "message: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void btnAttach_Click(object sender, EventArgs e)
        {
            
            string filename = Path.GetFileName(FileUpload1.PostedFile.FileName);
            string contentType = FileUpload1.PostedFile.ContentType;

            if (FileUpload1.HasFile)
            {
                using (Stream fs = FileUpload1.PostedFile.InputStream)
                {
                    using (BinaryReader br = new BinaryReader(fs))
                    {
                        byte[] bytes = br.ReadBytes((Int32)fs.Length);
                        string constr = ConfigurationManager.ConnectionStrings["CoffeeConnectionString"].ConnectionString;
                        using (SqlConnection con = new SqlConnection(constr))
                        {
                            string query = "insert into Resume values (@Name, @ContentType, @Data, @LoginID)";
                            using (SqlCommand cmd = new SqlCommand(query))
                            {
                                cmd.Connection = con;
                                cmd.Parameters.AddWithValue("@Name", filename);
                                cmd.Parameters.AddWithValue("@ContentType", contentType);
                                cmd.Parameters.AddWithValue("@Data", bytes);
                                cmd.Parameters.AddWithValue("@LoginID", loginid);
                                con.Open();
                                cmd.ExecuteNonQuery();
                                con.Close();
                            }
                        }
                    }
                }
                Response.Redirect(Request.Url.AbsoluteUri);
            }
                
            else
               
                lblError.Text = "You did not choose a file to upload.";
            
        }        
    }
}