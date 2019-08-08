<%@ Page MaintainScrollPositionOnPostback=true Title="New Account" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="NewUser.aspx.cs" Inherits="UserList.NewUser" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="image">            
            <div class="container" >
                <a href="index.aspx"><img src="images/RS.jpg" alt="RSI logo"></a>
            </div>
        </div>
        
        <div class="navbar"> 
             <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.aspx">Human Resource Form</a>
                </div>
                 <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a runat="server" href="~/Index.aspx" style="font-size:large">Login</a></li>               
                </ul>
            </div>
            </div>
        </div>

    <div class="login" style="text-align: center">
        
        <div>
            <asp:TextBox runat="server" ID="txtNewName" placeholder="Username"
                CssClass="content" MaxLength="25" Style="width: 150px; padding: 4px"></asp:TextBox>
            <div>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" Display="Dynamic"
                    ControlToValidate="txtNewName" CssClass="text-danger" ErrorMessage="Username is required.">
                </asp:RequiredFieldValidator>
            </div>
        </div>
        <br />
        <div>
            <asp:TextBox runat="server" ID="txtNewPass" placeholder="Password"
                CssClass="content" MaxLength="25" Style="width: 150px"></asp:TextBox>
            <div>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" Display="Dynamic"
                    ControlToValidate="txtNewPass" CssClass="text-danger" ErrorMessage="Password is required.">
                </asp:RequiredFieldValidator>
            </div>
        </div>
        <br />
        <div>
            <asp:TextBox runat="server" ID="txtEmail" placeholder="E-mail"
                CssClass="content" MaxLength="25" Style="width: 150px"></asp:TextBox>
            
        </div>
        <br />
        <div>
            <asp:Label ID="lblnewAccess" runat="server" Text="Access Level: "></asp:Label>
            <asp:DropDownList ID="txtAccess" runat="server" style="padding: 4px">

                <asp:ListItem Value="Applicant">Applicant</asp:ListItem>
                <asp:ListItem Value="Admin">Admin</asp:ListItem>
                <asp:ListItem Value="HR">HR</asp:ListItem>
            </asp:DropDownList>
            
        </div>
        <br />

        <asp:SqlDataSource runat="server" ID="SqlDataSource1"
            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
            InsertCommand="INSERT INTO [Login] ([LoginID], [Password], [Access])
            VALUES (@LoginID, @Password, @Access)">

            <InsertParameters>
                <asp:Parameter Name="LoginID" Type="String" />
                <asp:Parameter Name="Password" Type="String" />
                <asp:Parameter Name="Access" Type="String" />
            </InsertParameters>

        </asp:SqlDataSource>

        <asp:SqlDataSource runat="server" ID="SqlDataSource2"
            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'
            InsertCommand="INSERT INTO [Users] ([LoginID], [Active])
            VALUES (@LoginID, @Active)">

            <InsertParameters>
                <asp:Parameter Name="LoginID" Type="String" />
                <asp:Parameter Name="Active" Type="String" />
            </InsertParameters>

        </asp:SqlDataSource>

        <div>
            <asp:Label ID="lblError" runat="server"
                CssClass="text-danger" EnableViewState="false" Visible="false">
            </asp:Label>
        </div>

        <div class="form-group" style="margin-top: 10px">

            <div>
                <asp:Button ID="btnCreate" runat="server" Text="Create Account"
                    class="button" OnClick="btnCreate_Click" />

                <asp:Button ID="btnCancel" runat="server" Text="Back to Login" CausesValidation="false"
                    class="button" OnClick="btnCancel_Click" />
            </div>
        </div>
    </div>
</asp:Content>
