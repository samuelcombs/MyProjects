<%@ Page MaintainScrollPositionOnPostback=true Title="RSI Project" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="UserList.Index" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

        <div class="image">            
            <div class="container" >
                <a href="index.aspx"><img src="images/RS.jpg" alt="RSI logo"></a>
            </div>
        </div>
        
        <div class="navbar"> 
             <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="Index.aspx" style="font-size:large">Human Resource Form</a>
                </div>
            </div>
        </div>
    <div class="login" style="text-align: center">
    <div>
        <asp:TextBox runat="server" ID="txtUsername" placeholder="Username"
            CssClass="content" MaxLength="50" Style="width: 160px; padding: 4px"></asp:TextBox>
        <div>
        <asp:RequiredFieldValidator ID="rUsername" runat="server" Display="Dynamic"
            ControlToValidate="txtUsername" CssClass="text-danger" ErrorMessage="Username is required.">
        </asp:RequiredFieldValidator>
        </div>
    </div>
        <br />
    <div>
        <asp:TextBox runat="server" ID="txtPassword" placeholder="Password"
            CssClass="content" MaxLength="50" Style="width: 160px; padding: 4px"></asp:TextBox>
        <div>
        <asp:RequiredFieldValidator ID="rPassword" runat="server" Display="Dynamic"
            ControlToValidate="txtPassword" CssClass="text-danger" ErrorMessage="Password is required.">
        </asp:RequiredFieldValidator>
        </div>
    </div>

        <asp:SqlDataSource runat="server" ID="SqlDataSource1"
            ConnectionString='<%$ ConnectionStrings:CoffeeConnectionString %>'>            

        </asp:SqlDataSource>

        <br />
    <div>
        <asp:Label ID="lblInvalid" runat="server"
                CssClass="text-danger" EnableViewState="false" Text="Invalid Login" Visible="false">
        </asp:Label>
    </div>

    <div class="form-group" style="margin-top: 10px">      
        
        <div>
            <asp:Button ID="btnNew" runat="server" Text="New Account"
                class="button" OnClick="btnNew_Click" CausesValidation="false"/>

            <asp:Button ID="btnLogin" runat="server" Text="Login"
                class="button" OnClick="btnLogin_Click" />

        </div>
    </div>                
    </div>
    <div style="text-align:center">  
        
       <br />
    <label>username: admin</label><br />
    <label>pass: 123</label>
        <br />
        <label>username: hr</label><br />
    <label>pass: 123</label>
         <br />
        <label>username: sam</label><br />
    <label>pass: 123</label>
        <br />
        <label>username: jon</label><br />
    <label>pass: 123</label>

    </div>

</asp:Content>
