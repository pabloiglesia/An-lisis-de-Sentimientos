<%@page import="asr.proyectoFinal.dominio.EmotionAnalysis"%>
<%@page import="java.util.Iterator"%>
<%@include file="includes/head.jsp" %>    
<%@include file="includes/header.jsp" %>
<%@include file="includes/sidebar.jsp" %>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Historial
        <small>Aquí encontrarás todas las cartas de motivación que has almacenado.</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li class="active">Historial</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
		<div class="box">
            <div class="box-header">
              <h3 class="box-title">Historial</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table table-condensed">
                <tbody><tr>
                  <th style="width: 10px">#</th>
                  <th>Candidato</th>
                  <th>Empresa</th>
                  <th>Alegría</th>
                  <th>Tristeza</th>
                  <th>Temor</th>
                  <th>Disgusto</th>
                  <th>Enfado</th>
                  <th style="width: 100px">Acción</th>
                </tr>
     	
	             <% Iterator<EmotionAnalysis> itr = (Iterator<EmotionAnalysis>) session.getAttribute("record"); 
	             	int i=0;
					while(itr.hasNext()) {
						i++;
				        EmotionAnalysis element = (EmotionAnalysis) itr.next(); %>
				        <tr>
		                  <td><%= i %></td>
		                  <td><%= element.getCandidate() %></td>
		                  <td><%= element.getCompany() %></td>
		                  <td>
		                    <div class="progress progress-xs">
		                      <div class="progress-bar progress-bar-warning" style="width: <%= 100*element.getGeneralResults().getJoy() %>%"></div>
		                    </div>
		                  </td>
		                  <td>
		                    <div class="progress progress-xs">
		                      <div class="progress-bar progress-bar-aqua" style="width: <%= 100*element.getGeneralResults().getSadness() %>%"></div>
		                    </div>
		                  </td>
		                  <td>
		                    <div class="progress progress-xs">
		                      <div class="progress-bar progress-bar-brown" style="width: <%= 100*element.getGeneralResults().getFear() %>%"></div>
		                    </div>
		                  </td>		  
		                  <td>
		                    <div class="progress progress-xs">
		                      <div class="progress-bar progress-bar-success" style="width: <%= 100*element.getGeneralResults().getDisgust() %>%"></div>
		                    </div>
		                  </td>		  
		                  <td>
		                    <div class="progress progress-xs">
		                      <div class="progress-bar progress-bar-red" style="width: <%= 100*element.getGeneralResults().getAnger() %>%"</div>
		                    </div>
		                  </td>		                  
		                  <td><a class="btn btn-block btn-primary btn-sm" href="?id=<%= element.get_id() %>">Ver detalles</a></td>
		                  
		                </tr>
				  <% } %>
				  
				                  
              </tbody></table>
            </div>
            <!-- /.box-body -->
          </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>