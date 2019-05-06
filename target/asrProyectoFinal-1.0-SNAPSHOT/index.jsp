<%@include file="includes/head.jsp" %>    
<%@include file="includes/header.jsp" %>
<%@include file="includes/sidebar.jsp" %>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Añadir carta de motivación
        <small>Con este formulario puedes añadir una carta de motivación para analizar su contenido.</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li class="active">Añadir carta</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

     	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Añadir carta</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="POST" action="/asrProyectoFinal/insert/">
              <div class="box-body">
              	<div class="form-group">
                  <label>Nombre de tu empresa:</label>
                  <input name="companyName" type="text" class="form-control" placeholder="Ej: ICAI" required>
                </div>
                
                <div class="form-group">
                  <label>Carta de motivación</label>
<<<<<<< HEAD
                  <textarea name="text" class="form-control" rows="3" placeholder="Escribe aquí la carta de recomendación" required></textarea>
=======
                  <textarea name="text" class="form-control" rows="11" placeholder="Escribe aquí la carta de recomendación" required></textarea>
>>>>>>> a29775130d7adfe8dddd13b476fb08bfc4deae8a
                </div>

				<div class="form-group">
                  <label>¿En que idioma está la carta?</label>
                  <select name="language" class="form-control">
					<option value="es">Español</option>
<<<<<<< HEAD
					<option value="en">Inglés</option>
=======
					<option value="es">Inglés</option>
>>>>>>> a29775130d7adfe8dddd13b476fb08bfc4deae8a
					<option value="fr">Francés</option>
					<option value="de">Alemán</option>
					<option value="pt">Portugués</option>
                  </select>
                </div>
                
              	<label>Añadir palabras clave separadas por comas:</label>
                
	            <div class="form-group">
	                <!-- /btn-group -->
<<<<<<< HEAD
	                <input type="text" class="form-control" placeholder="Ej: Finanzas,Big Data,...">
=======
	                <input name="keywords" type="text" class="form-control" placeholder="Ej: Finanzas,Big Data,...">
>>>>>>> a29775130d7adfe8dddd13b476fb08bfc4deae8a
	              </div>
              </div>
              <!-- /.box-body -->
                
              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Añadir</button>
              </div>
            </form>
          </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>