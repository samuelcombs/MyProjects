<%@ Page Title="Applicant List" MaintainScrollPositionOnPostback=true Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ApplicantList.aspx.cs" Inherits="UserList.ApplicantList" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="image">
        <div class="container">
            <a href="index.aspx">
                <img src="images/RS.jpg" alt="RSI logo"></a>
        </div>
    </div>

    <div class="navbar">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="applicantList.aspx" style="font-size:large">Applicant List</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a runat="server" href="~/Index.aspx" style="font-size:large">Logout</a></li>
                    <li><a runat="server" href="~/Stats.aspx" style="font-size:large">Stats</a></li>
                </ul>
            </div>
        </div>
    </div>
        
    <div class="col-xs-12 col-sm-12 col-lg-12">

        <asp:GridView ID="grdUsers" runat="server" AllowPaging="True" AllowSorting="True"
            AutoGenerateColumns="False" DataKeyNames="UserID, LoginID" DataSourceID="SqlDataSource1" OnRowDataBound="grdUsers_RowDataBound" 
            CssClass="table table-bordered table-striped table-hover" 
            Style="-webkit-box-shadow: 5px 5px 15px 5px #000000; box-shadow: 5px 5px 15px 5px #000000; font-size: large; border: 1px solid black; border-radius: 10px">

            <Columns>
                
                <asp:TemplateField SortExpression="UserID" ConvertEmptyStringToNull="False">                    
                    <ItemTemplate>
                        <asp:Label ID="lblCount" runat="server"></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="LoginID" SortExpression="LoginID" ConvertEmptyStringToNull="False" Visible="false">                    
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="LoginIDSort" runat="server" Text="LoginID" CommandName="Sort" CommandArgument="LoginID" />
                    </HeaderTemplate>
                    <ItemTemplate>                       
                        <asp:Label ID="lblLoginID" runat="server" Text='<%# Eval("LoginID") %>'></asp:Label>

                        <asp:SqlDataSource runat="server" ID="SqlDataSource3"
                            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
                            SelectCommand="SELECT Id, Name FROM Resume WHERE (LoginID = @Lid)">
                            <SelectParameters>
                                <asp:ControlParameter ControlID="lblLoginID" Name="Lid" PropertyName="Text" />
                            </SelectParameters>
                        </asp:SqlDataSource>
                        
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField> 

                <asp:TemplateField HeaderText="UserID" SortExpression="UserID" ConvertEmptyStringToNull="False" Visible="false">                    
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="UserIDSort" runat="server" Text="UserID" CommandName="Sort" CommandArgument="UserID" />
                    </HeaderTemplate>
                    <ItemTemplate>                       
                        <asp:Label ID="lblUserID" runat="server" Text='<%# Eval("UserID") %>'></asp:Label>

                        <asp:SqlDataSource runat="server" ID="SqlDataSource2"
                            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
                            SelectCommand="SELECT SkillName, SkillYears, SkillMonths FROM UserSkills WHERE (UserID = @Uid)">
                            <SelectParameters>
                                <asp:ControlParameter ControlID="lblUserID" Name="Uid" PropertyName="Text" />
                            </SelectParameters>
                        </asp:SqlDataSource>
                        
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>                

                <asp:TemplateField HeaderText="fName" SortExpression="FirstName" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="fNameSort" runat="server" Text="First Name" CommandName="Sort" CommandArgument="FirstName" />
                    </HeaderTemplate>
                    <EditItemTemplate>                        
                            <asp:TextBox ID="txtFirstName" runat="server" CssClass="form-control" Text='<%# Bind("FirstName") %>'></asp:TextBox>                        
                        <asp:RequiredFieldValidator ID="rFirstName" runat="server" Display="Dynamic"
                            ControlToValidate="txtFirstName" CssClass="text-danger" ErrorMessage="First Name is required.">*
                        </asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblFirstName" runat="server" Text='<%# Eval("FirstName") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="MI" SortExpression="MiddleName" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="MISort" runat="server" Text="Middle Initial" CommandName="Sort" CommandArgument="MiddleName" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtMiddleName" runat="server" CssClass="form-control" Text='<%# Bind("MiddleName") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblMiddleName" runat="server" Text='<%# Eval("MiddleName") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="lName" SortExpression="LastName" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="lNameSort" runat="server" Text="Last Name" CommandName="Sort" CommandArgument="LastName" />
                    </HeaderTemplate>
                    <EditItemTemplate>                        
                            <asp:TextBox ID="txtLastName" runat="server" CssClass="form-control" Text='<%# Bind("LastName") %>'></asp:TextBox>                       
                        <asp:RequiredFieldValidator ID="rLastName" runat="server" Display="Dynamic"
                            ControlToValidate="txtLastName" CssClass="text-danger" ErrorMessage="Last Name is required.">*
                        </asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblLastName" runat="server" Text='<%# Eval("LastName") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Suff" SortExpression="Suffix" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="SuffSort" runat="server" Text="Suffix" CommandName="Sort" CommandArgument="Suffix" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtSuffix" runat="server" CssClass="form-control" Text='<%# Bind("Suffix") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblSuffix" runat="server" Text='<%# Eval("Suffix") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="ActiveTimeOnList" SortExpression="ActiveTime" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="ActiveTimeSort" runat="server" Text="Active Days On List" CommandName="Sort" CommandArgument="ActiveTime" />
                    </HeaderTemplate>
                    <EditItemTemplate>                        
                            <asp:TextBox ID="txtActiveTime" runat="server" CssClass="form-control" Text='<%# Bind("ActiveTime") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblActiveTime" runat="server" Text='<%# Eval("ActiveTime") %>'></asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Active" SortExpression="Active" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="ActiveOnListSort" runat="server" Text="Active" CommandName="Sort" CommandArgument="Active" />
                    </HeaderTemplate>
                    <EditItemTemplate>
                        <asp:DropDownList ID="ddlActive" runat="server"
                            SelectedValue='<%# Bind("Active") %>'
                            AppendDataBoundItems="true">
                            <asp:ListItem Value="Yes">Yes</asp:ListItem>
                            <asp:ListItem Value="No">No</asp:ListItem>
                        </asp:DropDownList>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblActive" runat="server" Text='<%# Eval("Active") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="DatePlaced" SortExpression="DatePlaced" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="DatePlacedSort" runat="server" Text="Date Placed" CommandName="Sort" CommandArgument="DatePlaced" />
                    </HeaderTemplate>
                    <EditItemTemplate>                        
                            <asp:TextBox ID="txtDatePlaced" runat="server" CssClass="form-control" Text='<%# Bind("DatePlaced") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblDatePlaced" runat="server" Text='<%# Eval("DatePlaced") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="CurrentLoc" SortExpression="CurrentLoc" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="CurrentLoc" runat="server" Text="Current Work Location" CommandName="Sort" CommandArgument="CurrentLoc" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtCurrentLoc" runat="server" CssClass="form-control" Text='<%# Bind("CurrentLoc") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblCurrentLoc" runat="server" Text='<%# Eval("CurrentLoc") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="CurrentArea" SortExpression="CurrentArea" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="CurrentAreaSort" runat="server" Text="Current Practice Area" CommandName="Sort" CommandArgument="CurrentArea" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtCurrentArea" runat="server" CssClass="form-control" Text='<%# Bind("CurrentArea") %>'></asp:TextBox>   
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblCurrentArea" runat="server" Text='<%# Eval("CurrentArea") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Years" SortExpression="PracticeYears" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="PracticeYearsSort" runat="server" Text="Years" CommandName="Sort" CommandArgument="PracticeYears" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtPracticeYears" runat="server" CssClass="form-control" Text='<%# Bind("PracticeYears") %>'></asp:TextBox>                       
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblPracticeYears" runat="server" Text='<%# Eval("PracticeYears") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Months" SortExpression="PracticeMonths" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="PracticeMonthsSort" runat="server" Text="Months" CommandName="Sort" CommandArgument="PracticeMonths" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtPracticeMonths" runat="server" CssClass="form-control" Text='<%# Bind("PracticeMonths") %>'></asp:TextBox>                        
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblPracticeMonths" runat="server" Text='<%# Eval("PracticeMonths") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="small text-center" />
                    <ItemStyle CssClass="small text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="DesiredLoc" SortExpression="DesiredLoc" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="DesiredLocSort" runat="server" Text="Desired Work Location" CommandName="Sort" CommandArgument="DesiredLoc" />
                    </HeaderTemplate>
                    <EditItemTemplate>                       
                            <asp:TextBox ID="txtDesiredLoc" runat="server" CssClass="form-control" Text='<%# Bind("DesiredLoc") %>'></asp:TextBox>                                                
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblDesiredLoc" runat="server" Text='<%# Eval("DesiredLoc") %>'>
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="DesiredArea" SortExpression="DesiredArea" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="DesiredAreaSort" runat="server" Text="Desired Practice Area" CommandName="Sort" CommandArgument="DesiredArea" />
                    </HeaderTemplate>
                    <EditItemTemplate>
                         <asp:TextBox ID="txtDesiredArea" runat="server" CssClass="form-control" Text='<%# Bind("DesiredArea") %>'></asp:TextBox>                                              
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="lblDesiredArea" runat="server" Text='<%# Eval("DesiredArea") %>'>                            
                        </asp:Label>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Skills" SortExpression="SkillName" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" Text="Current Skills" runat="server"/>
                    </HeaderTemplate>
                    <ItemTemplate>
                        <div style="overflow-y: unset; overflow-x: hidden; max-height: 140px; max-width: 240px; margin: auto">
                        <asp:GridView ID="gvSkills" runat="server" AutoGenerateColumns="false" DataKeyNames="SkillName" AllowPaging="false"
                            DataSourceID="SqlDataSource2" GridLines="None" Style="padding: 0px; margin: 0px; font-size:small">
                            <Columns>
                                <asp:TemplateField HeaderText="Name">
                                    <ItemTemplate>
                                        <asp:Label ID="lblSkillName" runat="server" Text='<%# Eval("SkillName") %>'></asp:Label>
                                        <HeaderStyle CssClass="text-center"/>
                                    </ItemTemplate>
                                    </asp:TemplateField>
                                <asp:TemplateField HeaderText="Years" >
                                    <ItemTemplate>
                                        <asp:Label ID="lblSkillYears" runat="server" Text='<%# Eval("SkillYears") %>'></asp:Label>
                                        <HeaderStyle CssClass="text-center"/>
                                    </ItemTemplate>
                                    </asp:TemplateField>
                                    <asp:TemplateField HeaderText="Months" >
                                    <ItemTemplate>
                                        <asp:Label ID="lblSkillMonths" runat="server" Text='<%# Eval("SkillMonths") %>'></asp:Label>
                                        <HeaderStyle CssClass="text-center"/>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                            </div>
                    </ItemTemplate>
                    <HeaderStyle CssClass="col-sm-2 text-center" />
                    <ItemStyle CssClass="col-sm-2 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Resume" SortExpression="Resume" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" runat="server" Text="Resume" />
                    </HeaderTemplate>

                    <ItemTemplate>
                        <asp:GridView ID="GridView1" runat="server" GridLines="None" ShowHeader="false" Style="padding: 0px; margin: 0px; font-size:small"
                            AutoGenerateColumns="false" DataSourceID="SqlDataSource3" DataKeyNames="Id, Name">
                            <Columns>
                                <%--<asp:BoundField DataField="Name" HeaderText="File Name" />--%>

                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <asp:Label ID="lblname" runat="server" Text='<%# Eval("Name") %>'></asp:Label>
                                        <HeaderStyle CssClass="text-center"/>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                
                                <asp:TemplateField>
                                    <ItemTemplate>                                                                                
                                        <asp:LinkButton ID="lnkDownload" runat="server" Text="Download" OnClick="DownloadFile"
                                            CommandArgument='<%# Eval("Id") %>'></asp:LinkButton>
                                        <HeaderStyle CssClass="text-center"/>
                                    </ItemTemplate>                                    
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </ItemTemplate>

                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Comments" SortExpression="Comments" ConvertEmptyStringToNull="False">
                    <HeaderTemplate>
                        <asp:LinkButton CssClass="head" ID="CommentsSort" runat="server" Text="Comments" CommandName="Sort" CommandArgument="Comments" />
                    </HeaderTemplate>                    
                    <ItemTemplate>
                        <div style="overflow-y: unset; overflow-x: hidden; max-height: 140px; max-width: 140px; margin: auto">                                
                            <asp:Label ID="lblComments" runat="server" Text='<%# Eval("Comments") %>' style="max-width: 140px; word-wrap:break-word"></asp:Label>
                            </div>
                    </ItemTemplate>
                    <EditItemTemplate>
                            <asp:TextBox ID="txtComments" runat="server" CssClass="form-control" Text='<%# Bind("Comments") %>' style="max-width: inherit"></asp:TextBox>                                                
                    </EditItemTemplate>
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:TemplateField>

                <asp:CommandField
                    CausesValidation="true"
                    ShowEditButton="true">
                    <HeaderStyle CssClass="col-sm-1 text-center" />
                    <ItemStyle CssClass="col-sm-1 text-center" />
                </asp:CommandField>

                <asp:TemplateField HeaderText="">
                    <ItemTemplate>
                        <asp:Button ID="deleteButton" runat="server" class="button"
                            CommandName="Delete" Text="Delete"
                            OnClientClick="return confirm('Are you sure you want to delete this record?');" />
                    </ItemTemplate>
                </asp:TemplateField>

            </Columns>        
            </asp:GridView>

        <asp:SqlDataSource runat="server" ID="SqlDataSource1"
            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
            DeleteCommand="DELETE FROM [UserSkills] WHERE [UserID] = @UserID; DELETE FROM [Users] WHERE [UserID] = @UserID; DELETE FROM [Login] WHERE [LoginID] = @LoginID; DELETE FROM [RESUME] WHERE [LoginID] = @LoginID"
            InsertCommand="INSERT INTO [Users] ([LastName], [FirstName], [MiddleName], [Suffix], [ActiveTime], [Active], [DatePlaced], [CurrentLoc], [CurrentArea], [PracticeYears], [PracticeMonths], [DesiredLoc], [DesiredArea], [LoginID])
            VALUES (@LastName, @FirstName, @MiddleName, @Suffix, @ActiveTime, @Active, @DatePlaced, @CurrentLoc, @CurrentArea, @PracticeYears, @PracticeMonths, @DesiredLoc, @DesiredArea, @LoginID)"
            SelectCommand="SELECT * FROM [Users] ORDER BY [UserID]"
            UpdateCommand="UPDATE [Users] SET [LastName] = @LastName, [FirstName] = @FirstName, [MiddleName] = @MiddleName, [Suffix] = @Suffix, 
            [ActiveTime] = @ActiveTime, [Active] = @Active, [DatePlaced] = @DatePlaced, [CurrentLoc] = @CurrentLoc, [CurrentArea] = @CurrentArea, [Comments] = @Comments,
            [PracticeYears] = @PracticeYears, [PracticeMonths] = @PracticeMonths, [DesiredLoc] = @DesiredLoc, [DesiredArea] = @DesiredArea, [LoginID] = @LoginID WHERE [UserID] = @UserID">
            <DeleteParameters>
                <asp:Parameter Name="LoginID" Type="String" />
            </DeleteParameters>            
            <UpdateParameters>
                <asp:Parameter Name="UserID" Type="Int32" />
                <asp:Parameter Name="LastName" Type="String" />
                <asp:Parameter Name="FirstName" Type="String" />
                <asp:Parameter Name="MiddleName" Type="String" />
                <asp:Parameter Name="Suffix" Type="String" />
                <asp:Parameter Name="ActiveTime" Type="String" />
                <asp:Parameter Name="Active" Type="String" />
                <asp:Parameter Name="DatePlaced" Type="String" />
                <asp:Parameter Name="CurrentLoc" Type="String" />
                <asp:Parameter Name="CurrentArea" Type="String" />
                <asp:Parameter Name="PracticeYears" Type="Int32" />
                <asp:Parameter Name="PracticeMonths" Type="Int32" />
                <asp:Parameter Name="DesiredLoc" Type="String" />
                <asp:Parameter Name="DesiredArea" Type="String" />
                <asp:Parameter Name="LoginID" Type="String" />
                <asp:Parameter Name="Comments" Type="String" />
            </UpdateParameters>
        </asp:SqlDataSource>
        
    </div>
    <br />
    <div>
        <p>
            <asp:Label ID="lblError" runat="server"
                CssClass="text-danger" EnableViewState="false">
            </asp:Label>
        </p>
        <asp:ValidationSummary ID="ValidationSummary1" runat="server"
            HeaderText="Please correct the following errors:"
            CssClass="text-danger" />        
    </div>
    
</asp:Content>
