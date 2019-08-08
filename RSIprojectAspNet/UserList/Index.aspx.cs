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
    public partial class Index : System.Web.UI.Page
    {
        
        protected void Page_Load(object sender, EventArgs e)
        {
            lblInvalid.Visible = false;
            if (!IsPostBack)
            {
                if (Session["Access"] != null) {
                    Session["Access"] = null;
                }
            }
        }
        protected void btnLogin_Click(object sender, EventArgs e)
        {

            using (SqlConnection con = new SqlConnection
                (@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\Coffee.mdf;Integrated Security=True;Connect Timeout=30"))
            {
                try
                {
                    con.Open();
                    string login = "SELECT COUNT(1) FROM LOGIN WHERE LoginID=@LoginID and Password=@Password";
                    SqlCommand sqlCmd = new SqlCommand(login, con);
                    string access = "SELECT Access FROM LOGIN WHERE LoginID=@LoginID";
                    SqlCommand sqlCmd2 = new SqlCommand(access, con);
                    sqlCmd.Parameters.AddWithValue("@LoginID", txtUsername.Text.Trim());
                    sqlCmd.Parameters.AddWithValue("@Password", txtPassword.Text.Trim());
                    sqlCmd2.Parameters.AddWithValue("@LoginID", txtUsername.Text.Trim());
                    int count = Convert.ToInt32(sqlCmd.ExecuteScalar());
                    if (count == 1)
                    {
                        string ac = (string)sqlCmd2.ExecuteScalar();                        

                        Session["Access"] = ac;                        

                        if (ac == "Admin" || ac == "HR")
                            Response.Redirect("applicantList.aspx?loginid=" + txtUsername.Text.Trim());
                        else
                            Response.Redirect("applicantForm.aspx?loginid=" + txtUsername.Text.Trim());
                    }
                    else
                    {
                        lblInvalid.Visible = true;
                    }
                    con.Close();
                }
                catch (Exception ex)
                {
                    lblInvalid.Text = "A database error has occurred. " +
                        "Message: " + ex.Message;
                }
            }
        }
        protected void btnNew_Click(object sender, EventArgs e)
        {
            Response.Redirect("newUser.aspx");
        }
    }
}