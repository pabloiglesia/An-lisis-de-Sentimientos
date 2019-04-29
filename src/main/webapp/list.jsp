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

     	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Historial</h3>
            </div>
            <!-- /.box-header -->
             <div class="box-body">
	             <% Iterator<EmotionAnalysis> itr = (Iterator<EmotionAnalysis>) session.getAttribute("record"); 
					while(itr.hasNext()) {
				        EmotionAnalysis element = (EmotionAnalysis) itr.next(); %>
				        <%= element %><br><hr>
				  <% } %>
             </div>
         </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>