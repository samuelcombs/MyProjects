using System;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using System.Data.SqlClient;

namespace UserList
{
    public partial class NewUser : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
           
        }

        //protected void btnCreate_Click(object sender, EventArgs e)
        //{
        //    if (IsValid)
        //    {
        //        string strConnString = ConfigurationManager.ConnectionStrings
        //            ["CoffeeConnectionString"].ConnectionString;
        //        SqlCommand com;

        //        SqlConnection con = new SqlConnection(strConnString);
        //        com = new SqlCommand("insert1", con);
        //        com.CommandType = CommandType.StoredProcedure;
        //        com.Parameters.Add("@Username", SqlDbType.VarChar).Value = txtNewName.Text;
        //        com.Parameters.Add("@Password", SqlDbType.VarChar).Value = txtNewPass.Text;
        //        com.Parameters.Add("@Email", SqlDbType.VarChar).Value = txtEmail.Text;
        //        com.Parameters.Add("@Access", SqlDbType.VarChar).Value = txtAccess.Text;
        //        con.Open();
        //        com.ExecuteNonQuery();
        //        con.Close();

        //        com = new SqlCommand("insert2", con);
        //        com.CommandType = CommandType.StoredProcedure;
        //        com.Parameters.AddWithValue("@UserID", SqlDbType.Int);
        //        com.Parameters.Add("@Username", SqlDbType.VarChar).Value = txtNewName.Text;
        //        con.Open();
        //        com.ExecuteNonQuery();
        //        con.Close();
        //        lblError.Text = "New User Created";
        //        lblError.Visible = true;
                
        //    }
            
        //}

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }

        protected void btnCreate_Click(object sender, EventArgs e)
        {
            if (IsValid)
            {
                var parameters1 = SqlDataSource1.InsertParameters;
                var parameters2 = SqlDataSource2.InsertParameters;

                parameters1["LoginID"].DefaultValue = txtNewName.Text;
                parameters1["Password"].DefaultValue = txtNewPass.Text;
                parameters1["Access"].DefaultValue = txtAccess.SelectedValue;

                parameters2["LoginID"].DefaultValue = txtNewName.Text;
                parameters2["Active"].DefaultValue = "No";

                try
                {

                    SqlDataSource1.Insert();
                    if (txtAccess.SelectedValue == "Applicant")
                    SqlDataSource2.Insert();

                    lblError.Text = "New user created";
                    lblError.Visible = true;

                }
                catch (Exception ex)
                {
                    lblError.Text = "A database error has occurred. " +
                        "message: " + ex.Message;
                    lblError.Visible = true;
                }
            }
        }       
    }
}