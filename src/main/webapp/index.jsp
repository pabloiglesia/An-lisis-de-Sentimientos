<%@include file="includes/head.jsp" %>    
<%@include file="includes/header.jsp" %>
<%@include file="includes/sidebar.jsp" %>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        A�adir carta de motivaci�n
        <small>Con este formulario puedes a�adir una carta de motivaci�n para analizar su contenido.</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li class="active">A�adir carta</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

     	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">A�adir carta</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="POST" action="/asrProyectoFinal/insert">
              <div class="box-body">
              	<div class="form-group">
                  <label>Nombre de tu empresa:</label>
                  <input name="companyName" type="text" class="form-control" placeholder="Ej: ICAI" required>
                </div>
                
                <div class="form-group">
                  <label>Nombre del candidato:</label>
                  <input name="candidate" type="text" class="form-control" placeholder="Ej: John Doe" required>
                </div>
                
                <div class="form-group">
                  <label>Carta de motivaci�n</label>
                  <textarea name="text" class="form-control" rows="11" placeholder="Escribe aqu� la carta de recomendaci�n" required></textarea>
                </div>

				<div class="form-group">
                  <label>�En que idioma est� la carta?</label>
                  <select name="language" class="form-control">
					<option value="es">Espa�ol</option>
					<option value="es">Ingl�s</option>
					<option value="fr">Franc�s</option>
					<option value="de">Alem�n</option>
					<option value="pt">Portugu�s</option>
                  </select>
                </div>
                
              	<label>A�adir palabras clave separadas por comas:</label>
                
	            <div class="form-group">
	                <!-- /btn-group -->
	                <input name="keywords" type="text" class="form-control" placeholder="Ej: Finanzas,Big Data,...">
	              </div>
              </div>
              <!-- /.box-body -->
                
              <div class="box-footer">
                <button type="submit" class="btn btn-primary">A�adir</button>
              </div>
            </form>
          </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>