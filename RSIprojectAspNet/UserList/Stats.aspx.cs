using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace UserList
{
    public partial class Stats : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            using (SqlConnection con = new SqlConnection
                (@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\Coffee.mdf;Integrated Security=True;Connect Timeout=30"))
            {
                try
                {
                    con.Open();
                    string all = "SELECT COUNT(Access) FROM Login";
                    SqlCommand sqlCmd = new SqlCommand(all, con);
                    int countAccounts = Convert.ToInt32(sqlCmd.ExecuteScalar());
                    lblUsers.Text = "Total Accounts: " + countAccounts.ToString();
                    string admin = "SELECT COUNT(Access) FROM Login WHERE Access = 'Admin'";
                    SqlCommand sqlCmd2 = new SqlCommand(admin, con);
                    int countAdmins = Convert.ToInt32(sqlCmd2.ExecuteScalar());
                    lblAdmins.Text = "Total Admins: " + countAdmins.ToString();
                    string hr = "SELECT COUNT(Access) FROM Login WHERE Access ='HR'";
                    SqlCommand sqlCmd3 = new SqlCommand(hr, con);
                    int countHR = Convert.ToInt32(sqlCmd3.ExecuteScalar());
                    lblHR.Text = "Total HR: " + countHR.ToString();
                    string applicant = "SELECT COUNT(Access) FROM Login WHERE Access = 'Applicant'";
                    SqlCommand sqlCmd4 = new SqlCommand(applicant, con);
                    int countApplicants = Convert.ToInt32(sqlCmd4.ExecuteScalar());
                    lblApplicants.Text = "Total Applicants: " + countApplicants.ToString();

                    con.Close();
                }
                catch (Exception ex)
                {
                    lblError.Text = "A database error has occurred. " +
                        "Message: " + ex.Message;
                }
            }
        }
    }
}