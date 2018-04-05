<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title">휴가 목록</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
          	<div class="col-sm-6">
          		분류:&ensp;<select id="condition">
          			<option value="">전체</option>
          			<option value="미승인">미승인</option>
          			<option value="승인">승인</option>
          			<option value="거절">거절</option>
          		</select>
          	</div>
          	<!-- ./col -->
          	<div class="col-xs-6">
          		<span >총 사용가능한 휴가일수: </span><span class="pull-right" id="totalHoliday" >${sessionScope.staffVO.positionVO.pHolidayCount}</span> 
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          <div class="row">
          	<div class="col-sm-6 pull-right">
          		<span>사용한 휴가일수:</span><span class="pull-right"  id="useHoliday" > ${requestScope.useHoliday} </span> 
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          <div class="row">
          	<div class="col-sm-6 pull-right">
          		<span >남은 휴가일수: </span> <span id="extraHoliday" class="pull-right">${sessionScope.staffVO.positionVO.pHolidayCount-requestScope.useHoliday}</span>
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          
          
          
          
          <table id="holiday-list" class="table table-hover table-bordered">
            <thead>
            <tr>
              <th>NO</th>
              <th>신청자</th>
              <th>휴가 일자</th>
              <th>신청 일자</th>
              <th>승인 여부</th>
              <th>비고</th>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="holiday" items="${requestScope.listVO.list}" >
            		<tr class="index" data-param="${holiday.hNo}">
            			<td>${holiday.hNo}</td>
            			<td>${holiday.staffVO.name}</td>
            			<td>${holiday.hStartDate} ~ ${holiday.hEndDate}</td>
            			<td>${holiday.hRegDate}</td>
            			<td>${holiday.hFlag}</td>
            			<td>${holiday.hReason}</td>
            		</tr>
            	</c:forEach>
            </tbody>
          </table>
          <c:set value="${requestScope.listVO.pagingBean }" var="bean"/>
          <div class="row">
          	<div class="col-xs-12 text-center">
          		<ul class="pagination">  
		<%--prevButton --%>
			<c:if test="${bean.isPreviousPageGroup()==true}">
				<li><a href="dispatcher?command=read_holiday&pageNo=${bean.getStartPageOfPageGroup()-1}">&laquo;</a></li>
			</c:if>
		<%--pageButton --%>
		<c:forEach var="page" begin="${bean.getStartPageOfPageGroup()}" end="${bean.getEndPageOfPageGroup()}">
			<c:choose>
			<c:when test="${bean.nowPage == page}"><li class="active"><a href="#">${page}</a></li></c:when>
			<c:otherwise>
				<li><a href="dispatcher?command=read_holiday&pageNo=${page}">${page}</a></li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--nextButton --%>
		<c:if test="${bean.isNextPageGroup()==true}">
			<li><a href="dispatcher?command=read_holiday&pageNo=${bean.getEndPageOfPageGroup()+1}">&raquo;</a></li>
		</c:if>
	</ul>
          	</div>
          	<!-- /.col -->
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
    </div>
    <!-- /.col -->
  </div>
  <!-- /.row -->
</section>
<!-- /.content -->

<!--  inline chart -->
<div class="row">
<div class="col-xs-6 text-center">
 <div style="display:inline; width:90px;height:90px;">
<input type="text" class="knob hUseChart" value="" data-width="90" data-height="90" data-fgcolor="#f56954" style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; background: none; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(147, 42, 182); padding: 0px; -webkit-appearance: none;">
</div>
<div class="knob-label">휴가 사용 정도</div>
</div>

<div class="col-xs-6 text-center">
 <div style="display:inline; width:90px;height:90px;">
<input type="text" class="knob hRestChart" value="" data-width="90" data-height="90" data-fgcolor="#00a65a" style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; background: none; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(147, 42, 182); padding: 0px; -webkit-appearance: none;">
</div>
<div class="knob-label">휴가 남은 정도</div>
</div>
</div>



<!-- condition script -->
<script>
$(document).ready(function(){
	var select = '${param.condition}';
	//rendering
	$( '#condition option' ).each(function() {
		if($( this ).val()==select){
			$( this ).prop('selected',true); 
		} else{
			$( this ).prop('selected',false);
		}
	});
	
	$( '#condition' ).change(function() {
		var condition=$(this).val();
		if(condition!=null && condition!=''){
			location.href='dispatcher?command=read_holiday&condition=' + condition;
		} else{
			location.href='dispatcher?command=read_holiday';
		}
	});
});
</script>

<script type="text/javascript">
	//id="extraHoliday" useHoliday totalHoliday
	var extraHoliday = $("#extraHoliday").text();
	var totalHoliday = $("#totalHoliday").text();
	var useHoliday = $("#useHoliday").text();
	
	console.log("extraHoliday: "+extraHoliday);
	console.log("totalHoliday: "+totalHoliday);
	console.log("useHoliday: "+useHoliday);
	
	//var cnt = (extHoliday/totalHoliday)*100;
	var perUseHoliday = Math.round((useHoliday/totalHoliday)*100);
	var perRestHoliday = Math.round((extraHoliday/totalHoliday)*100);
	$(".hUseChart").val(perUseHoliday+"%");
	$(".hRestChart").val(perRestHoliday+"%");

</script>

<!-- knob script -->
 <script src="${pageContext.request.contextPath}/dist/js/knob.js"></script>
<!-- <script>
  $(function () {
    /* jQueryKnob */

    $(".knob").knob({
      /*change : function (value) {
       //console.log("change : " + value);
       },
       release : function (value) {
       console.log("release : " + value);
       },
       cancel : function () {
       console.log("cancel : " + this.value);
       },*/
      draw: function () {

        // "tron" case
        if (this.$.data('skin') == 'tron') {

          var a = this.angle(this.cv)  // Angle
              , sa = this.startAngle          // Previous start angle
              , sat = this.startAngle         // Start angle
              , ea                            // Previous end angle
              , eat = sat + a                 // End angle
              , r = true;

          this.g.lineWidth = this.lineWidth;

          this.o.cursor
          && (sat = eat - 0.3)
          && (eat = eat + 0.3);

          if (this.o.displayPrevious) {
            ea = this.startAngle + this.angle(this.value);
            this.o.cursor
            && (sa = ea - 0.3)
            && (ea = ea + 0.3);
            this.g.beginPath();
            this.g.strokeStyle = this.previousColor;
            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
            this.g.stroke();
          }

          this.g.beginPath();
          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
          this.g.stroke();

          this.g.lineWidth = 2;
          this.g.beginPath();
          this.g.strokeStyle = this.o.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
          this.g.stroke();

          return false;
        }
      }
    });
    /* END JQUERY KNOB */

    //INITIALIZE SPARKLINE CHARTS
    $(".sparkline").each(function () {
      var $this = $(this);
      $this.sparkline('html', $this.data());
    });

    /* SPARKLINE DOCUMENTATION EXAMPLES http://omnipotent.net/jquery.sparkline/#s-about */
    drawDocSparklines();
    drawMouseSpeedDemo();

  });
  function drawDocSparklines() {

    // Bar + line composite charts
    $('#compositebar').sparkline('html', {type: 'bar', barColor: '#aaf'});
    $('#compositebar').sparkline([4, 1, 5, 7, 9, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 2, 5, 6, 7],
        {composite: true, fillColor: false, lineColor: 'red'});


    // Line charts taking their values from the tag
    $('.sparkline-1').sparkline();

    // Larger line charts for the docs
    $('.largeline').sparkline('html',
        {type: 'line', height: '2.5em', width: '4em'});

    // Customized line chart
    $('#linecustom').sparkline('html',
        {
          height: '1.5em', width: '8em', lineColor: '#f00', fillColor: '#ffa',
          minSpotColor: false, maxSpotColor: false, spotColor: '#77f', spotRadius: 3
        });

    // Bar charts using inline values
    $('.sparkbar').sparkline('html', {type: 'bar'});

    $('.barformat').sparkline([1, 3, 5, 3, 8], {
      type: 'bar',
      tooltipFormat: '{{value:levels}} - {{value}}',
      tooltipValueLookups: {
        levels: $.range_map({':2': 'Low', '3:6': 'Medium', '7:': 'High'})
      }
    });

    // Tri-state charts using inline values
    $('.sparktristate').sparkline('html', {type: 'tristate'});
    $('.sparktristatecols').sparkline('html',
        {type: 'tristate', colorMap: {'-2': '#fa7', '2': '#44f'}});

    // Composite line charts, the second using values supplied via javascript
    $('#compositeline').sparkline('html', {fillColor: false, changeRangeMin: 0, chartRangeMax: 10});
    $('#compositeline').sparkline([4, 1, 5, 7, 9, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 2, 5, 6, 7],
        {composite: true, fillColor: false, lineColor: 'red', changeRangeMin: 0, chartRangeMax: 10});

    // Line charts with normal range marker
    $('#normalline').sparkline('html',
        {fillColor: false, normalRangeMin: -1, normalRangeMax: 8});
    $('#normalExample').sparkline('html',
        {fillColor: false, normalRangeMin: 80, normalRangeMax: 95, normalRangeColor: '#4f4'});

    // Discrete charts
    $('.discrete1').sparkline('html',
        {type: 'discrete', lineColor: 'blue', xwidth: 18});
    $('#discrete2').sparkline('html',
        {type: 'discrete', lineColor: 'blue', thresholdColor: 'red', thresholdValue: 4});

    // Bullet charts
    $('.sparkbullet').sparkline('html', {type: 'bullet'});

    // Pie charts
    $('.sparkpie').sparkline('html', {type: 'pie', height: '1.0em'});

    // Box plots
    $('.sparkboxplot').sparkline('html', {type: 'box'});
    $('.sparkboxplotraw').sparkline([1, 3, 5, 8, 10, 15, 18],
        {type: 'box', raw: true, showOutliers: true, target: 6});

    // Box plot with specific field order
    $('.boxfieldorder').sparkline('html', {
      type: 'box',
      tooltipFormatFieldlist: ['med', 'lq', 'uq'],
      tooltipFormatFieldlistKey: 'field'
    });

    // click event demo sparkline
    $('.clickdemo').sparkline();
    $('.clickdemo').bind('sparklineClick', function (ev) {
      var sparkline = ev.sparklines[0],
          region = sparkline.getCurrentRegionFields();
      value = region.y;
      alert("Clicked on x=" + region.x + " y=" + region.y);
    });

    // mouseover event demo sparkline
    $('.mouseoverdemo').sparkline();
    $('.mouseoverdemo').bind('sparklineRegionChange', function (ev) {
      var sparkline = ev.sparklines[0],
          region = sparkline.getCurrentRegionFields();
      value = region.y;
      $('.mouseoverregion').text("x=" + region.x + " y=" + region.y);
    }).bind('mouseleave', function () {
      $('.mouseoverregion').text('');
    });
  }

  /**
   ** Draw the little mouse speed animated graph
   ** This just attaches a handler to the mousemove event to see
   ** (roughly) how far the mouse has moved
   ** and then updates the display a couple of times a second via
   ** setTimeout()
   **/
  function drawMouseSpeedDemo() {
    var mrefreshinterval = 500; // update display every 500ms
    var lastmousex = -1;
    var lastmousey = -1;
    var lastmousetime;
    var mousetravel = 0;
    var mpoints = [];
    var mpoints_max = 30;
    $('html').mousemove(function (e) {
      var mousex = e.pageX;
      var mousey = e.pageY;
      if (lastmousex > -1) {
        mousetravel += Math.max(Math.abs(mousex - lastmousex), Math.abs(mousey - lastmousey));
      }
      lastmousex = mousex;
      lastmousey = mousey;
    });
    var mdraw = function () {
      var md = new Date();
      var timenow = md.getTime();
      if (lastmousetime && lastmousetime != timenow) {
        var pps = Math.round(mousetravel / (timenow - lastmousetime) * 1000);
        mpoints.push(pps);
        if (mpoints.length > mpoints_max)
          mpoints.splice(0, 1);
        mousetravel = 0;
        $('#mousespeed').sparkline(mpoints, {width: mpoints.length * 2, tooltipSuffix: ' pixels per second'});
      }
      lastmousetime = timenow;
      setTimeout(mdraw, mrefreshinterval);
    };
    // We could use setInterval instead, but I prefer to do it this way
    setTimeout(mdraw, mrefreshinterval);
  }
</script> -->

<%@ include file="detail_holiday.jsp" %>
<%@ include file="delete_holiday.jsp" %>
<%@ include file="deny_holiday.jsp" %>
    