<%@ Page MaintainScrollPositionOnPostback=true Title="Statistics"  Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Stats.aspx.cs" Inherits="UserList.Stats" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="image">
        <div class="container">
            <a href="index.aspx">
                <img src="images/RS.jpg" alt="RSI logo"></a>
        </div>
    </div>

    <div class="navbar">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="~/stats.aspx" style="font-size:large">Statistics</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a runat="server" href="~/Index.aspx" style="font-size:large">Logout</a></li>
                    <li><a runat="server" href="~/ApplicantList.aspx" style="font-size:large">Applicant List</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="login" style="text-align: center">
        <div>
            <asp:Label Style="font-size: large; font-weight: bold" runat="server" ID="lblUsers"></asp:Label>
        </div>

        <div>
            <asp:Label Style="font-size: large" runat="server" ID="lblAdmins"></asp:Label>
        </div>

        <div>
            <asp:Label Style="font-size: large" runat="server" ID="lblHR"></asp:Label>
        </div>

        <div>
            <asp:Label Style="font-size: large" runat="server" ID="lblApplicants"></asp:Label>
        </div>

        <div>
            <asp:Label runat="server" ID="lblError"></asp:Label>          
        </div>
        
    </div>       
</asp:Content>
