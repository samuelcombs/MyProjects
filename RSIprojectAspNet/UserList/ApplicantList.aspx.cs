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
    public partial class ApplicantList : System.Web.UI.Page
    {        
        SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\Coffee.mdf;Integrated Security=True;Connect Timeout=30");

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Access"].ToString() == "Applicant" || Session["Access"] == null)
            {
                Response.Redirect("index.aspx");
            } 
        }

        public void grdUsers_RowDataBound(Object sender, GridViewRowEventArgs e)
        {            
            for (int i = 0; i < grdUsers.Rows.Count; i++)
            {                
                grdUsers.Rows[i].Cells[0].Text = (i + 1).ToString();
                if (Session["Access"].ToString() == "HR")
                {
                    grdUsers.Columns[19].Visible = false;
                    grdUsers.Columns[20].Visible = false;
                }
            }
            //if (e.Row.RowType != DataControlRowType.DataRow) return;

            //GridView GridView1 = (GridView)e.Row.FindControl("GridView1");

            //string constr = ConfigurationManager.ConnectionStrings["CoffeeConnectionString"].ConnectionString;

            //using (SqlConnection con = new SqlConnection(constr))
            //{
            //    using (SqlCommand cmd = new SqlCommand())
            //    {
            //        cmd.CommandText = "SELECT Id, Name from Resume WHERE UserID=@UserID";
                    
            //        cmd.Parameters.AddWithValue("@UserID", loginid);

            //        cmd.Connection = con;
            //        con.Open();
            //        GridView1.DataSource = cmd.ExecuteReader();
            //        GridView1.DataBind();
            //        con.Close();
            //    }
            //}
        }
                

        protected void DownloadFile(object sender, EventArgs e)
        {
            int id = int.Parse((sender as LinkButton).CommandArgument);
            byte[] bytes;
            string fileName, contentType;
            string constr = ConfigurationManager.ConnectionStrings["CoffeeConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand())
                {
                    cmd.CommandText = "select Name, Data, ContentType from Resume where Id=@Id";
                    cmd.Parameters.AddWithValue("@Id", id);
                    cmd.Connection = con;
                    con.Open();
                    using (SqlDataReader sdr = cmd.ExecuteReader())
                    {
                        sdr.Read();
                        bytes = (byte[])sdr["Data"];
                        contentType = sdr["ContentType"].ToString();
                        fileName = sdr["Name"].ToString();
                    }
                    con.Close();
                }
            }
            Response.Clear();
            Response.Buffer = true;
            Response.Charset = "";
            Response.Cache.SetCacheability(HttpCacheability.NoCache);
            Response.ContentType = contentType;
            Response.AppendHeader("Content-Disposition", "attachment; filename=" + fileName);
            Response.BinaryWrite(bytes);
            Response.Flush();
            Response.End();
        }
                
    }
}