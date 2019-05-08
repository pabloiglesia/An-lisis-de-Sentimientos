<%@page import="asr.proyectoFinal.dominio.EmotionAnalysis"%>
<%@page import="com.ibm.watson.developer_cloud.personality_insights.v3.model.Trait" %>
<%@page import="java.util.Iterator"%>
<%@include file="includes/head.jsp" %>    
<%@include file="includes/header.jsp" %>
<%@include file="includes/sidebar.jsp" %>

<!-- Content Wrapper. Contains page content -->

	<% EmotionAnalysis analysis = (EmotionAnalysis) session.getAttribute("analysis"); %>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Resultado del estudio
        <small>Este es el resultado del estudio que hemos realizado sobre la carta de motivación.</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li class="active">Resultados</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
    	<div class="row">	
		        <div class="col-lg-3 col-md-6 col-xs-12">
		          <!-- small box -->
		          <div class="small-box bg-aqua">
		            <div class="inner text-center">
		              <h3><%= analysis.getPersonalities().get(0).getName() %></h3>
		              <h4><%=analysis.getPersonalities().get(0).getRawScore().floatValue() %></h4>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-md-6 col-xs-12">
		          <!-- small box -->
		          <div class="small-box bg-green">
		            <div class="inner text-center">
		              <h3 style="font-size:33px;height:42px"><%=analysis.getPersonalities().get(1).getName() %></h3>
		              <h4><%=analysis.getPersonalities().get(1).getRawScore().floatValue() %><sup style="font-size: 20px"></sup></h4>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-md-6 col-xs-12">
		          <!-- small box -->
		          <div class="small-box bg-yellow">
		            <div class="inner text-center">
		              <h3><%=analysis.getPersonalities().get(2).getName() %></h3>
		              <h4><%=analysis.getPersonalities().get(2).getRawScore().floatValue() %></h4>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-md-6 col-xs-12">
		          <!-- small box -->
		          <div class="small-box bg-red">
		            <div class="inner text-center">
		              <h3><%=analysis.getPersonalities().get(3).getName() %></h3>
		              <h4><%=analysis.getPersonalities().get(3).getRawScore().floatValue() %></h4>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		      </div>
    	<!-- Carta de motivación -->
    	<div class="box box-widget">
            <div class="box-header with-border row">
              <div class="user-block col-md-9">
                <span class="username">Texto de la carta</span>
              </div>
                <form method="POST" action="/asrProyectoFinal/translate"> 
                	<input type="hidden" value="<%= analysis.get_id() %>" name="id">    
					<div class="col-md-2">
						<select name="language" class="btn-block form-control">
							<option value="es">Español</option>
							<option value="en">Inglés</option>
							<option value="fr">Francés</option>
							<option value="de">Alemán</option>
							<option value="pt">Portugués</option>
		                  </select>
					</div>		
					<div class="col-md-1">
						<input type="submit" class="btn btn-block btn-primary" value="Traducir">
					</div>			
				</form>
				
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <!-- post text -->
              <p>
				<%= analysis.getTextAsHTML() %>
			  </p>

            </div>
            
            <!-- /.box-footer -->
            <div class="box-footer">
            <p>
              <span class="h4">En la carta se habla de: </span>
              <% for (int i=0; i<analysis.getCategories().size(); i++) {  %>
	              <button type="button" class="btn btn-primary"><%= analysis.getCategories().get(i).getLabel().split("/")[1] %></button>
	              <button type="button" class="btn btn-primary"><%= analysis.getCategories().get(i).getLabel().split("/")[2] %></button>
              <% } %>
              </p>
            </div>
            <!-- /.box-footer -->
          </div>    	
    	<!-- End Carta de motivación -->

		<!-- Emociones generales -->
     	<div class="collapsed-box box box-primary">
            <div class="box-header with-border" data-widget="collapse">
              <h3 class="box-title">Emociones generales</h3>
              	<div class="box-tools pull-right">
				  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				  </button>
				</div>
            </div>
            <!-- /.box-header -->
             <div class="box-body">
             	<div class="row">
             	<!-- Generales de la carta -->
	             <div class="col-md-6">
			          <div class="box box-primary box-solid">
			            <div class="box-header with-border">
			              <h3 class="box-title">Emociones generales de la carta:</h3>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body">
			              <h4>Enfado:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-red" role="progressbar" style="width: <%= 100*analysis.getGeneralResults().getAnger() %>%">
			                  <span class="sr-only">40% Complete (success)</span>
			                </div>
			              </div>
			              <h4>Disgusto:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-green" role="progressbar" style="width: <%= 100*analysis.getGeneralResults().getDisgust() %>%">
			                  <span class="sr-only">20% Complete</span>
			                </div>
			              </div>
			              <h4>Temor:</h4>			             			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-brown" role="progressbar" style="width: <%= 100*analysis.getGeneralResults().getFear() %>%">
			                  <span class="sr-only">60% Complete (warning)</span>
			                </div>
			              </div>
			              <h4>Alegría:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-yellow" role="progressbar" style="width: <%= 100*analysis.getGeneralResults().getJoy() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			              <h4>Tristeza:</h4>			              			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-aqua" role="progressbar" style="width: <%= 100*analysis.getGeneralResults().getSadness() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			            </div>
			            <!-- /.box-body -->
			          </div>
			          <!-- /.box -->
			        </div>
			        <!-- End Generales de la carta -->
			        
			        <!-- Hacia la empresa -->			        
	             	<div class="col-md-6">
	             		<% 
	             		boolean companyResults = true;
	             		if(analysis.getTargetResults().get(0).getTargetName().equals(analysis.getCompany())){ 
	             		%>
			          <div class="box box-primary box-solid">
			            <div class="box-header with-border">
			              <h3 class="box-title">Emociones hacia la empresa:</h3>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body">
			              <h4>Enfado:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-red" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(0).getTargetResults().getAnger() %>%">
			                  <span class="sr-only">40% Complete (success)</span>
			                </div>
			              </div>
			              <h4>Aversión:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-green" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(0).getTargetResults().getDisgust() %>%">
			                  <span class="sr-only">20% Complete</span>
			                </div>
			              </div>
			              <h4>Temor:</h4>			             			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-brown" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(0).getTargetResults().getFear() %>%">
			                  <span class="sr-only">60% Complete (warning)</span>
			                </div>
			              </div>
			              <h4>Alegría:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-yellow" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(0).getTargetResults().getJoy() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			              <h4>Tristeza:</h4>			              			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-aqua" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(0).getTargetResults().getSadness() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			            </div>
			            <!-- /.box-body -->
			          </div>
			          <!-- /.box -->
			          	<% } else { 
			          		companyResults = false;
			          	%>
			          		<div class="alert alert-warning alert-dismissible">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				                <h4><i class="icon fa fa-warning"></i> Lo sentimos!</h4>
				                En la carta no se habla lo suficiente de la empresa como para realizar un análisis de sentimientos.
				              </div>
			          	<% } %>
			        </div>
				</div>
				<!-- End Hacia la empresa -->
             </div>
         </div>
        <!-- End Emociones generales -->
         
        <!-- Análisis de palabras clave -->
     	<div class="collapsed-box box box-success">
            <div class="box-header with-border" data-widget="collapse">
              <h3 class="box-title">Análisis de palabras clave</h3>
				<div class="box-tools pull-right">
				  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				  </button>
				</div>
            </div>
            <!-- /.box-header -->
             <div class="box-body">
             	<div class="row">
             	<% boolean noKeywordResults = false;
         		int j=1; 
             	if(companyResults){
             		if(analysis.getTargetResults().size()<=j) {
             			noKeywordResults = true;
             		}
             	} else {
             		j=0; 
             		if(analysis.getTargetResults().size()<=j) {
             			noKeywordResults = true;
             		}
             	}
             	%>
             		
             		<% if(noKeywordResults){ %>
        				<div class="alert alert-warning alert-dismissible" style="margin:15px">
		                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
		                <h4><i class="icon fa fa-warning"></i> Lo sentimos!</h4>
		                No hemos obtenido ningún resultado reseñable en el análisis de sentimientos de las palabras clave indicadas. Es probable que el candidato no hable de temas relacionados con estas palabras en su carta.
		              </div>
             		
             	<% } %>
             	<% for(int i=j; i<analysis.getTargetResults().size(); i++) { %>
             	<!-- PALABRA CLAVE -->
	             <div class="col-md-6">
			          <div class="box box-success box-solid">
			            <div class="box-header with-border">
			              <h3 class="box-title"><%= analysis.getTargetResults().get(i).getTargetName() %></h3>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body">
			              <h4>Enfado:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-red" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(i).getTargetResults().getAnger() %>%">
			                  <span class="sr-only">40% Complete (success)</span>
			                </div>
			              </div>
			              <h4>Disgusto:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-green" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(i).getTargetResults().getDisgust() %>%">
			                  <span class="sr-only">20% Complete</span>
			                </div>
			              </div>
			              <h4>Temor:</h4>			             			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-brown" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(i).getTargetResults().getFear() %>%">
			                  <span class="sr-only">60% Complete (warning)</span>
			                </div>
			              </div>
			              <h4>Alegría:</h4>			             
			              <div class="progress">
			                <div class="progress-bar progress-bar-yellow" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(i).getTargetResults().getJoy() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			              <h4>Tristeza:</h4>			              			              
			              <div class="progress">
			                <div class="progress-bar progress-bar-aqua" role="progressbar" style="width: <%= 100*analysis.getTargetResults().get(i).getTargetResults().getSadness() %>%">
			                  <span class="sr-only">80% Complete</span>
			                </div>
			              </div>
			            </div>
			            <!-- /.box-body -->
			          </div>
			          <!-- /.box -->
			        </div>
			        <!-- End PALABRA CLAVE -->
			   	<% } %>
             </div>
         </div>
         </div>
        <!-- End Análisis de palabras clave -->
        
	    <!-- Analisis de tono -->
		<div class="box box-info">
		  <div class="box box-primary collapsed-box">
		    <h3 class="box-title">Latest Orders</h3>
		
		    <div class="box-tools pull-right">
		      <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
		      </button>
		    </div>
		  </div>
		  <!-- /.box-header -->
		  <div class="box-body" style="">
		    <div class="table-responsive">
		      <table class="table no-margin">
		        <thead>
		        <tr>
		          <th>ID</th>
		          <th>Frase</th>
		          <th>Tonos</th>
		          <th>Puntuación</th>
		        </tr>
		        </thead>
		        <tbody>
		        <% for (int i=1; i<analysis.getSentenceAnalysis().size(); i++) { %>  
		        <tr>
		          <td><%=i%></td>
		          <td><a><%=analysis.getSentenceAnalysis().get(i).getText() %></a></td>
<%--   			  <td><%=analysis.getSentenceAnalysis().get(i).getTones().toArray()%></td> -->
<%-- 		       <td><span class="label label-success"><%=analysis.getSentenceAnalysis().get(i).getTones().get(i) %></span></td> --%>
		        </tr>
		        <% } %>
		        </tbody>
		      </table>
		    </div>
		    <!-- /.table-responsive -->
		  </div>
		  <!-- /.box-body -->
		</div>
		<!-- End Analisis de tono -->	 
  <!-- /.content -->
   </section>
</div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>