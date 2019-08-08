<%@ Page MaintainScrollPositionOnPostback=true Title="Applicant Form" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ApplicantForm.aspx.cs" Inherits="UserList.ApplicantForm" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="image">            
            <div class="container" >
                <a href="index.aspx"><img src="images/RS.jpg" alt="RSI logo"></a>
            </div>
        </div>
        
        <div class="navbar"> 
             <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand">Applicant Form</a>
                </div>
                 <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a runat="server" href="~/Index.aspx" style="font-size:large">Logout</a></li>                                       
                    </ul>
                </div>
            </div>
        </div>    
    <div class="apForm">    
        <div class="header">
            <h3>Applicant</h3>
        </div>
        <div class="section">                          
            
            <asp:TextBox runat="server" ID="txtFirstName" placeholder="First Name"
                CssClass="content" MaxLength="25" Style="width: 150px; padding: 4px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="rFirstName" runat="server" Display="Dynamic"
                ControlToValidate="txtFirstname" CssClass="text-danger" ErrorMessage="First Name is required."> </asp:RequiredFieldValidator>
                
            <asp:TextBox runat="server" ID="txtMiddleName" placeholder="M. Initial"
                CssClass="content" MaxLength="10" Style="width: 100px; padding: 4px"></asp:TextBox>
            <asp:TextBox runat="server" ID="txtLastName" placeholder="Last Name"
                CssClass="content" MaxLength="25" Style="width: 150px; padding: 4px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="rLastName" runat="server" Display="Dynamic"
                ControlToValidate="txtLastname" CssClass="text-danger" ErrorMessage="Last Name is required."> </asp:RequiredFieldValidator>
            <asp:TextBox runat="server" ID="txtSuffix" placeholder="Suffix"
                CssClass="content" MaxLength="10" Style="width: 80px; padding: 4px"></asp:TextBox>
                        
        </div>
        <br>
        <div class="header">
            <h3>Status</h3>
        </div>   
        <div class="section">
            <label>
                Active Status
            </label>

            <asp:Button ID="btnActive" runat="server" Style="top: 5px"
                class="addActive" OnClick="btnActive_Click" AutoPostback="false"/>
            
            <label style="margin-left: 10px">Active Days on List: </label>
            <asp:Label runat="server" ID="txtActiveTime"
                CssClass="content" Style="padding: 4px; font-weight:bold; color:red"></asp:Label>
            <label style="margin-left: 20px">Date Placed: </label>
            <asp:Label runat="server" ID="txtDatePlaced"
                CssClass="content" Style="padding: 4px; font-weight:bold; color:red"></asp:Label>
        </div>
        <br>
        <div class="header">
            <h3>Current Project
            </h3>
        </div>
        <div class="section">
            <asp:DropDownList ID="ddlCurrentLocation" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Current Work Location" Value=""></asp:ListItem>
                <asp:ListItem Text="Albuquerque, New Mexico" Value="Albuquerque, New Mexico"></asp:ListItem>
                <asp:ListItem Text="Augusta, Georgia" Value="Augusta, Georgia"></asp:ListItem>
                <asp:ListItem Text="Atlanta, Georgia" Value="Atlanta, Georgia"></asp:ListItem>
                <asp:ListItem Text="Fort Wayne, Texas" Value="Fort Wayne, Texas"></asp:ListItem>
                <asp:ListItem Text="Jonesboro, Arkansas" Value="Jonesboro, Arkansas"></asp:ListItem>
                <asp:ListItem Text="Mobile, Alabama" Value="Mobile, Alabama"></asp:ListItem>
                <asp:ListItem Text="Oklahoma City, Oklahoma" Value="Oklahoma City, Oklahoma"></asp:ListItem>
            </asp:DropDownList>            
            <asp:DropDownList ID="ddlCurrentPractice" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Current Practice Area" Value=""></asp:ListItem>
                <asp:ListItem Text=".NET" Value=".NET"></asp:ListItem>
                <asp:ListItem Text="Business Analyst" Value="Business Analyst"></asp:ListItem>
                <asp:ListItem Text="CAS" Value="CAS"></asp:ListItem>
                <asp:ListItem Text="Cloud Technologies : AWS" Value="Cloud Technologies : AWS"></asp:ListItem>
                <asp:ListItem Text="Database" Value="Database"></asp:ListItem>
                <asp:ListItem Text="Development: Low Code" Value="Development: Low Code"></asp:ListItem>
                <asp:ListItem Text="Java" Value="Java"></asp:ListItem>
                <asp:ListItem Text="LIMS" Value="LIMS"></asp:ListItem>
                <asp:ListItem Text="Mobile" Value="Mobile"></asp:ListItem>
                <asp:ListItem Text="Not Client Services" Value="Not Client Services"></asp:ListItem>
                <asp:ListItem Text="Project Management" Value="Project Management"></asp:ListItem>
                <asp:ListItem Text="QA Automation" Value="QA Automation"></asp:ListItem>
                <asp:ListItem Text="Quality Assurance" Value="Quality Assurance"></asp:ListItem>
                <asp:ListItem Text="Salesforce" Value="Salesforce"></asp:ListItem>
                <asp:ListItem Text="SAP-ABAP" Value="SAP-ABAP"></asp:ListItem>
                <asp:ListItem Text="SAP-Basis" Value="SAP-Basis"></asp:ListItem>
                <asp:ListItem Text="SAP-BI" Value="SAP-BI"></asp:ListItem>
                <asp:ListItem Text="SAP-Functional" Value="SAP-Functional"></asp:ListItem>
                <asp:ListItem Text="SharePoint" Value="SharePoint"></asp:ListItem>
                <asp:ListItem Text="Web" Value="Web"></asp:ListItem>
            </asp:DropDownList>
            <label style="margin-left: 25px">
                Active Time in Practice Area
            </label>
            <asp:DropDownList ID="ddlPracticeYears" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Years" Value=""></asp:ListItem>
                <asp:ListItem Value="1">1</asp:ListItem>
                <asp:ListItem Value="2">2</asp:ListItem>
                <asp:ListItem Value="3">3</asp:ListItem>
                <asp:ListItem Value="4">4</asp:ListItem>
                <asp:ListItem Value="5">5</asp:ListItem>
                <asp:ListItem Value="6">6</asp:ListItem>
                <asp:ListItem Value="7">7</asp:ListItem>
                <asp:ListItem Value="8">8</asp:ListItem>
                <asp:ListItem Value="9">9</asp:ListItem>
                <asp:ListItem Value="10">10</asp:ListItem>
                <asp:ListItem Value="11">11</asp:ListItem>
                <asp:ListItem Value="12">12</asp:ListItem>
                <asp:ListItem Value="13">13</asp:ListItem>
                <asp:ListItem Value="14">14</asp:ListItem>
                <asp:ListItem Value="15">15</asp:ListItem>
                <asp:ListItem Value="16">16</asp:ListItem>
                <asp:ListItem Value="17">17</asp:ListItem>
                <asp:ListItem Value="18">18</asp:ListItem>
                <asp:ListItem Value="19">19</asp:ListItem>
                <asp:ListItem Value="20">20</asp:ListItem>
            </asp:DropDownList>
            <asp:DropDownList ID="ddlPracticeMonths" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Months" Value=""></asp:ListItem>
                <asp:ListItem Value="1">1</asp:ListItem>
                <asp:ListItem Value="2">2</asp:ListItem>
                <asp:ListItem Value="3">3</asp:ListItem>
                <asp:ListItem Value="4">4</asp:ListItem>
                <asp:ListItem Value="5">5</asp:ListItem>
                <asp:ListItem Value="6">6</asp:ListItem>
                <asp:ListItem Value="7">7</asp:ListItem>
                <asp:ListItem Value="8">8</asp:ListItem>
                <asp:ListItem Value="9">9</asp:ListItem>
                <asp:ListItem Value="10">10</asp:ListItem>
                <asp:ListItem Value="11">11</asp:ListItem>
                <asp:ListItem Value="12">12</asp:ListItem>
            </asp:DropDownList>    
        </div>
        <br>
        <div class="header">
            <h3>Desired Project (Optional)</h3>
        </div>                
        <div class="section">
            <asp:DropDownList ID="ddlDesiredLocation" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Desired Work Location" Value=""></asp:ListItem>
                <asp:ListItem Text="Albuquerque, New Mexico" Value="Albuquerque, New Mexico"></asp:ListItem>
                <asp:ListItem Text="Augusta, Georgia" Value="Augusta, Georgia"></asp:ListItem>
                <asp:ListItem Text="Atlanta, Georgia" Value="Atlanta, Georgia"></asp:ListItem>
                <asp:ListItem Text="Fort Wayne, Texas" Value="Fort Wayne, Texas"></asp:ListItem>
                <asp:ListItem Text="Jonesboro, Arkansas" Value="Jonesboro, Arkansas"></asp:ListItem>
                <asp:ListItem Text="Mobile, Alabama" Value="Mobile, Alabama"></asp:ListItem>
                <asp:ListItem Text="Oklahoma City, Oklahoma" Value="Oklahoma City, Oklahoma"></asp:ListItem>
            </asp:DropDownList>
            <asp:DropDownList ID="ddlDesiredPractice" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Desired Practice Area" Value=""></asp:ListItem>
                <asp:ListItem Text=".NET" Value=".NET"></asp:ListItem>
                <asp:ListItem Text="Business Analyst" Value="Business Analyst"></asp:ListItem>
                <asp:ListItem Text="CAS" Value="CAS"></asp:ListItem>
                <asp:ListItem Text="Cloud Technologies : AWS" Value="Cloud Technologies : AWS"></asp:ListItem>
                <asp:ListItem Text="Database" Value="Database"></asp:ListItem>
                <asp:ListItem Text="Development: Low Code" Value="Development: Low Code"></asp:ListItem>
                <asp:ListItem Text="Java" Value="Java"></asp:ListItem>
                <asp:ListItem Text="LIMS" Value="LIMS"></asp:ListItem>
                <asp:ListItem Text="Mobile" Value="Mobile"></asp:ListItem>
                <asp:ListItem Text="Not Client Services" Value="Not Client Services"></asp:ListItem>
                <asp:ListItem Text="Project Management" Value="Project Management"></asp:ListItem>
                <asp:ListItem Text="QA Automation" Value="QA Automation"></asp:ListItem>
                <asp:ListItem Text="Quality Assurance" Value="Quality Assurance"></asp:ListItem>
                <asp:ListItem Text="Salesforce" Value="Salesforce"></asp:ListItem>
                <asp:ListItem Text="SAP-ABAP" Value="SAP-ABAP"></asp:ListItem>
                <asp:ListItem Text="SAP-Basis" Value="SAP-Basis"></asp:ListItem>
                <asp:ListItem Text="SAP-BI" Value="SAP-BI"></asp:ListItem>
                <asp:ListItem Text="SAP-Functional" Value="SAP-Functional"></asp:ListItem>
                <asp:ListItem Text="SharePoint" Value="SharePoint"></asp:ListItem>
                <asp:ListItem Text="Web" Value="Web"></asp:ListItem>
            </asp:DropDownList>
        </div>
        <br>
        <div class="header">
            <h3>Current Skills</h3>
        </div>
        <div class="col-xl-12 col-md-12 col-lg-12" style="padding-top: 6px; padding-bottom: 4px; margin: auto; width: 70%; font-size: 16px">

            <asp:GridView ID="grdSkills" runat="server" AllowPaging="False" AllowSorting="False"
            AutoGenerateColumns="False" DataKeyNames="UserSkillsID" DataSourceID="SqlDataSource1"
            CssClass="table table-" Style="padding: 0px; margin: 0px">
                <Columns>
                    
                    <asp:TemplateField HeaderText="Name">
                        <EditItemTemplate>                        
                            <asp:TextBox ID="txtSkillName" runat="server" CssClass="form-control" Text='<%# Bind("SkillName") %>'></asp:TextBox>                        
                        <asp:RequiredFieldValidator ID="rSkillName" runat="server" Display="Dynamic"
                            ControlToValidate="txtSkillName" CssClass="text-danger" ErrorMessage="Skill Name is required.">*
                        </asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblSkillName" runat="server" Text='<%# Eval("SkillName") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="text-center"/>
                    <ItemStyle CssClass="col-md-2" />
                </asp:TemplateField>

                    <asp:TemplateField HeaderText="Years">
                        <EditItemTemplate>
                        <asp:DropDownList ID="ddlSkillYears" runat="server"
                            SelectedValue='<%# Bind("SkillYears") %>'
                            AppendDataBoundItems="true">
                            <asp:ListItem Value="1">1</asp:ListItem>
                            <asp:ListItem Value="2">2</asp:ListItem>
                            <asp:ListItem Value="3">3</asp:ListItem>
                            <asp:ListItem Value="4">4</asp:ListItem>
                            <asp:ListItem Value="5">5</asp:ListItem>
                            <asp:ListItem Value="6">6</asp:ListItem>
                            <asp:ListItem Value="7">7</asp:ListItem>
                            <asp:ListItem Value="8">8</asp:ListItem>
                            <asp:ListItem Value="9">9</asp:ListItem>
                            <asp:ListItem Value="10">10</asp:ListItem>
                            <asp:ListItem Value="11">11</asp:ListItem>
                            <asp:ListItem Value="12">12</asp:ListItem>
                            <asp:ListItem Value="13">13</asp:ListItem>
                            <asp:ListItem Value="14">14</asp:ListItem>
                            <asp:ListItem Value="15">15</asp:ListItem>
                            <asp:ListItem Value="16">16</asp:ListItem>
                            <asp:ListItem Value="17">17</asp:ListItem>
                            <asp:ListItem Value="18">18</asp:ListItem>
                            <asp:ListItem Value="19">19</asp:ListItem>
                            <asp:ListItem Value="20">20</asp:ListItem>
                        </asp:DropDownList>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblSkillYears" runat="server" Text='<%# Eval("SkillYears") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="text-center"/>
                    <ItemStyle CssClass="col-md-1" />
                </asp:TemplateField>

                    <asp:TemplateField HeaderText="Months">
                        <EditItemTemplate>
                        <asp:DropDownList ID="ddlSkillMonths" runat="server"
                            SelectedValue='<%# Bind("SkillMonths") %>'
                            AppendDataBoundItems="true">
                            <asp:ListItem Value="1">1</asp:ListItem>
                            <asp:ListItem Value="2">2</asp:ListItem>
                            <asp:ListItem Value="3">3</asp:ListItem>
                            <asp:ListItem Value="4">4</asp:ListItem>
                            <asp:ListItem Value="5">5</asp:ListItem>
                            <asp:ListItem Value="6">6</asp:ListItem>
                            <asp:ListItem Value="7">7</asp:ListItem>
                            <asp:ListItem Value="8">8</asp:ListItem>
                            <asp:ListItem Value="9">9</asp:ListItem>
                            <asp:ListItem Value="10">10</asp:ListItem>
                            <asp:ListItem Value="11">11</asp:ListItem>
                            <asp:ListItem Value="12">12</asp:ListItem>
                        </asp:DropDownList>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblSkillMonths" runat="server" Text='<%# Eval("SkillMonths") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="text-center" />
                    <ItemStyle CssClass="col-md-1" />
                </asp:TemplateField>

                 <asp:CommandField 
                    CausesValidation="false"
                    ShowEditButton="true"
                    ShowDeleteButton="true">
                     <ItemStyle CssClass="col-md-1" />
                </asp:CommandField>               

            </Columns>
            </asp:GridView>

             <%--<asp:ListView ID="ListView1" runat="server" DataSourceID="SqlDataSource2">               
                </ItemTemplate>
                <ItemTemplate>
                        <asp:Label ID="lblSkillName" runat="server" Text='<%# Bind("SkillName") %>'></asp:Label>
                </ItemTemplate>                
                <ItemTemplate>
                        <asp:Label ID="lblSkillYears" runat="server" Text='<%# Bind("SkillYears") %>'></asp:Label>
                </ItemTemplate> 
                <ItemTemplate>
                        <asp:Label ID="lblSkillMonths" runat="server" Text='<%# Bind("SkillMonths") %>'></asp:Label>
                </ItemTemplate> 
            </asp:ListView>--%>

        </div>
        <div class="section">
            <asp:Button ID="btnSkill" runat="server" Text="+" Style="top: 5px"
                class="addSkill" OnClick="btnSkill_Click" />
            <asp:DropDownList ID="ddlSkills" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Skills for Desired Practice" Value=""></asp:ListItem>
                <asp:ListItem Text=".NET" Value=".NET"></asp:ListItem>
                <asp:ListItem Text="ABAP" Value="ABAP"></asp:ListItem>
                <asp:ListItem Text="Android" Value="Android"></asp:ListItem>
                <asp:ListItem Text="BASIS" Value="BASIS"></asp:ListItem>
                <asp:ListItem Text="Business Analysis" Value="Business Analysis"></asp:ListItem>
                <asp:ListItem Text="C#" Value="C#"></asp:ListItem>
                <asp:ListItem Text="C++" Value="C++"></asp:ListItem>
                <asp:ListItem Text="Data Analysis" Value="Data Analysis"></asp:ListItem>
                <asp:ListItem Text="File Maker" Value="File Maker"></asp:ListItem>
                <asp:ListItem Text="IT Analysis" Value="IT Analysis"></asp:ListItem>
                <asp:ListItem Text="JAVA" Value="JAVA"></asp:ListItem>
                <asp:ListItem Text="Javascript" Value="Javascript"></asp:ListItem>
                <asp:ListItem Text="JIRA" Value="JIRA"></asp:ListItem>
                <asp:ListItem Text="MySQL" Value="MySQL"></asp:ListItem>
                <asp:ListItem Text="Oracle" Value="Oracle"></asp:ListItem>
                <asp:ListItem Text="Outsystems" Value="Outsystems"></asp:ListItem>
                <asp:ListItem Text="PHP" Value="PHP"></asp:ListItem>
                <asp:ListItem Text="Project Coordination" Value="Project Coordination"></asp:ListItem>
                <asp:ListItem Text="Project Management" Value="Project Management"></asp:ListItem>
                <asp:ListItem Text="Quality Assurance" Value="Quality Assurance"></asp:ListItem>
                <asp:ListItem Text="Quick Base" Value="Quick Base"></asp:ListItem>
                <asp:ListItem Text="SalesForce" Value="SalesForce"></asp:ListItem>
                <asp:ListItem Text="Scrum Master" Value="Scrum Master"></asp:ListItem>
                <asp:ListItem Text="Selenium" Value="Selenium"></asp:ListItem>
                <asp:ListItem Text="Sharepoint" Value="Sharepoint"></asp:ListItem>
                <asp:ListItem Text="SQL" Value="SQL"></asp:ListItem>
                <asp:ListItem Text="Swift" Value="Swift"></asp:ListItem>
                <asp:ListItem Text="TFS" Value="TFS"></asp:ListItem>
                <asp:ListItem Text="UFT" Value="UFT"></asp:ListItem>
                <asp:ListItem Text="Other" Value="Other"></asp:ListItem>
                
            </asp:DropDownList>
            <asp:DropDownList ID="ddlSkillsYears" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Years" Value=""></asp:ListItem>
                <asp:ListItem Text="1" Value="1"></asp:ListItem>
                <asp:ListItem Text="2" Value="2"></asp:ListItem>
                <asp:ListItem Text="3" Value="3"></asp:ListItem>
                <asp:ListItem Text="4" Value="4"></asp:ListItem>
                <asp:ListItem Text="5" Value="5"></asp:ListItem>
                <asp:ListItem Text="6" Value="6"></asp:ListItem>
                <asp:ListItem Text="7" Value="7"></asp:ListItem>
                <asp:ListItem Text="8" Value="8"></asp:ListItem>
                <asp:ListItem Text="9" Value="9"></asp:ListItem>
                <asp:ListItem Text="10" Value="10"></asp:ListItem>
                <asp:ListItem Text="11" Value="11"></asp:ListItem>
                <asp:ListItem Text="12" Value="12"></asp:ListItem>
            </asp:DropDownList> 
            <asp:DropDownList ID="ddlSkillsMonths" runat="server" Style="padding: 4px">
                <asp:ListItem Text="Months" Value=""></asp:ListItem>
                <asp:ListItem Text="1" Value="1"></asp:ListItem>
                <asp:ListItem Text="2" Value="2"></asp:ListItem>
                <asp:ListItem Text="3" Value="3"></asp:ListItem>
                <asp:ListItem Text="4" Value="4"></asp:ListItem>
                <asp:ListItem Text="5" Value="5"></asp:ListItem>
                <asp:ListItem Text="6" Value="6"></asp:ListItem>
                <asp:ListItem Text="7" Value="7"></asp:ListItem>
                <asp:ListItem Text="8" Value="8"></asp:ListItem>
                <asp:ListItem Text="9" Value="9"></asp:ListItem>
                <asp:ListItem Text="10" Value="10"></asp:ListItem>
                <asp:ListItem Text="11" Value="11"></asp:ListItem>
                <asp:ListItem Text="12" Value="12"></asp:ListItem>
            </asp:DropDownList> 
            <asp:Textbox CssClass="comments" Rows="3" Columns="30" ID="comments" Maxlength="100" TextMode="MultiLine" runat="server"
                 placeholder="Additional Comments for Skills (100 Characters)"/>            
        </div>
        <br />
        
            <div>
            <asp:Label ID="lblError" runat="server" Style="top:15px"
                CssClass="text-danger" EnableViewState="false"> </asp:Label>
            <asp:ValidationSummary ID="ValidationSummary1" runat="server" style="margin-bottom: 5px"
            HeaderText="Please correct the following errors:"
            CssClass="text-danger" />
            <asp:Label ID="lbluserid" runat="server" EnableViewState="false" Visible="false"></asp:Label>
            </div>
       
        <div style="align-content: center; text-align: center; margin: auto; width: 20%; margin-bottom: 5px">
            <asp:FileUpload ID="FileUpload1" runat="server" />            
        </div>

            <asp:Button ID="btnAttach"
            runat="server" class="button"
            Text="Upload Resume" OnClick="btnAttach_Click"/>                  
        
            <asp:Button ID="btnSave" style="margin-left:20px; margin-right:20px"
            runat="server" class="button"
            Text="Save" OnClick="btnSave_Click"/>
         
            <asp:Button ID="btnClose"
            runat="server" class="button"
            Text="Logout" OnClick="btnClose_Click"/>

            
           </div>
    
    <asp:SqlDataSource runat="server" ID="SqlDataSource1"
            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
            DeleteCommand="DELETE FROM [UserSkills] WHERE [UserSkillsID] = @UserSkillsID"
            InsertCommand="INSERT INTO [UserSkills] ([UserID], [SkillName], [SkillYears], [SkillMonths])
            VALUES (@UserID, @SkillName, @SkillYears, @SkillMonths)"
            SelectCommand="SELECT * FROM [UserSkills] WHERE [UserID] = @UserID ORDER BY [UserSkillsID]"
            UpdateCommand="UPDATE [UserSkills] SET [SkillName] = @SkillName, [SkillYears] = @SkillYears, [SkillMonths] = @SkillMonths WHERE [UserSkillsID] = @UserSkillsID">
            <DeleteParameters>
                <asp:Parameter Name="UserSkillsID" Type="Int32" />
            </DeleteParameters>
            <SelectParameters>
                
                <asp:ControlParameter ControlID="lbluserid" Name="UserID" PropertyName="Text" Type="Int32" />
            </SelectParameters>

           <InsertParameters>
                <asp:Parameter Name="UserID" Type="Int32" />
                <asp:Parameter Name="SkillName" Type="String" />
                <asp:Parameter Name="SkillYears" Type="Int32" />
                <asp:Parameter Name="SkillMonths" Type="Int32" />                           
            </InsertParameters>
        
            <UpdateParameters>
                <asp:Parameter Name="UserID" Type="Int32" />
                <asp:Parameter Name="SkillName" Type="String" />
                <asp:Parameter Name="SkillYears" Type="Int32" />
                <asp:Parameter Name="SkillMonths" Type="Int32" />                           
            </UpdateParameters>

        </asp:SqlDataSource>   
        
</asp:Content>
