<%@page import="asr.proyectoFinal.dominio.EmotionAnalysis"%>
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
    
    	<!-- Carta de motivación -->
    	<div class="box box-widget">
            <div class="box-header with-border row">
              <div class="user-block col-md-9">
                <span class="username">Texto de la carta</span>
              </div>
                <form action="/translateCard">     
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
	              <button type="button" class="btn btn-primary"><%= analysis.getCategories().get(i).getLabel() %></button>
              <% } %>
              </p>
            </div>
            <!-- /.box-footer -->
          </div>    	
    	<!-- End Carta de motivación -->

		<!-- Emociones generales -->
     	<div class="collapsed-box box box-primary">
            <div class="box-header with-border">
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
			              <h4>Asco:</h4>			             
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
			        </div>
				</div>
				<!-- End Hacia la empresa -->
             </div>
         </div>
        <!-- End Emociones generales -->
         
        <!-- Análisis de palabras clave -->
     	<div class="collapsed-box box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Análisis de palabras clave</h3>
				<div class="box-tools pull-right">
				  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				  </button>
				</div>
            </div>
            <!-- /.box-header -->
             <div class="box-body">
             	<div class="row">
             	<% for (int i=1; i<analysis.getTargetResults().size(); i++) { %>
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
			              <h4>Asco:</h4>			             
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
        
    	<!-- Analisis de personalidad -->
	    <div class="box box-primary collapsed-box">
	        <div class="box-header with-border">
              <h3 class="box-title">Análisis de personalidad</h3>
              	<div class="box-tools pull-right">
				  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				  </button>
				</div>
			</div>
			<div class="box-body">
			 <div class="row">	
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div class="small-box bg-aqua">
		            <div class="inner">
		              <h3><%=analysis.getPersonalities().get(0).getRawScore() %></h3>
		              <p>Openess</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-bag"></i>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div class="small-box bg-green">
		            <div class="inner">
		              <h3>53<sup style="font-size: 20px">%</sup></h3>
		              <p>Conscientiousness</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-stats-bars"></i>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div class="small-box bg-yellow">
		            <div class="inner">
		              <h3>44</h3>
		              <p>Extraversion</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-person-add"></i>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div class="small-box bg-red">
		            <div class="inner">
		              <h3>65</h3>
		              <p>Agreeableness</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-pie-graph"></i>
		            </div>
		            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		      </div>
		     </div>
	     </div>
	    <!-- End Análisis de palabras clave -->
	    <!-- Analisis de tono -->
	    <div class="box box-primary collapsed-box">
	        <div class="box-header with-border">
              <h3 class="box-title">Tono de la carta</h3>
              	<div class="box-tools pull-right">
				  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				  </button>
				</div>
			</div>
		</div>	
		<!-- End Analisis de tono -->	 
  <!-- /.content -->
   </section>
</div>
  <!-- /.content-wrapper -->
  <%@include file="includes/footer.jsp" %>
  <%@include file="includes/foot.jsp" %>