<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Main content -->
    <section class="content">
    <!-- THE CALENDAR -->
      <div id="calendar" >
      </div>
    </section>
    <!-- /.content -->
    
<!-- Page specific script -->
<script>
  $(document).ready(function () {
	  
    /* initialize the external events
     -----------------------------------------------------------------*/
    function init_events(ele) {
      ele.each(function () {

        // it doesn't need to have a start or end
        var eventObject = {
          title: $.trim($(this).text()) // use the element's text as the event title
        }

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject)

        // make the event draggable using jQuery UI
        $(this).draggable({
          zIndex        : 1070,
          revert        : true, // will cause the event to go back to its
          revertDuration: 0  //  original position after the drag
        })
      })//each
    }//function

    init_events($('#external-events div.external-event'))

    /* initialize the calendar
     -----------------------------------------------------------------*/
    $('#calendar').fullCalendar({
      header    : {
        left  : '',
        center: 'title',
        right : 'month,agendaWeek,agendaDay'
      },
      buttonText: {
        today: 'today',
        month: 'month',
        week : 'week',
        day  : 'day'
      },
      editable  : false,
      displayEventTime: false,
      //Random default events
    });
    //fullCalendar
    
    // fc left안에 prev,next,today 버튼 생성
    $('.fc-left').append(
    		"<div class='fc-button-group'>"+
    		"<button type='button' id='prev-btn' class='fc-prev-button fc-button fc-state-default fc-corner-left'>"+
    		"<span class='fc-icon fc-icon-left-single-arrow'></span>"+
    		"</button>"+
    		"<button type='button' id='next-btn' class='fc-next-button fc-button fc-state-default fc-corner-right'>"+
    		"<span class='fc-icon fc-icon-right-single-arrow'></span>"+
    		"</button>"+
    		"</div>"+
    		"<button type='button' id='today-btn' class='fc-today-button fc-button fc-state-default fc-corner-left fc-corner-right fc-state-disabled	'>today</button>"
    ); //append
    
    getCalendar();
    
    //fc left에 생성한 버튼에 click 이벤트부여
    $('#prev-btn').click(function(){
    	$('#calendar').fullCalendar('prev');
    	getCalendar();
    	
    });
    $('#next-btn').click(function(){
    	$('#calendar').fullCalendar('next');
    	getCalendar();
    	
    });
    $('#today-btn').click(function(){
    	$('#calendar').fullCalendar('today');
   		getCalendar();
    });
   
  })
  //ready
</script>
<script>
function getCalendar(){
	var holidayList=[];
	var date = $('#calendar').fullCalendar('getDate')._d;
	var d    = date.getDate(),
	    m    = date.getMonth()+1,
	    y    = date.getFullYear();
	//ajax로 전체휴가목록을 가져와서 json array로 저장한다.  
	var holidayList=[];
	$.ajax({
		type:'get',
		url:'dispatcher',
		dataType:'json',
		data:'command=read_calendar&year='+y.toString()+'&month='+m.toString(),
		success:function(list){
			console.log(list);
			$.each(list,function(index,holiday){
				//상태에 따른 데이터 색상 설정
				var bgColor;
				if(holiday.hFlag=='미승인'){
					bgColor='#f56954'; //red
				} else{ //승인
					bgColor='#00a65a'; //Success (green)
				}
				console.log("sName "+holiday.staffVO.name);
				console.log("holiday.hFlag "+holiday.hFlag);
				holidayList.push({
			      title          : holiday.staffVO.name,
		          start          : moment(holiday.hStartDate,'YYYY-MM-DD')._d,
		          end            : moment(holiday.hEndDate,'YYYY-MM-DD')._d+1,
		          backgroundColor: bgColor,
		          borderColor    : bgColor
				});//push
			});//each
			//caleandar init
			$('#calendar').fullCalendar('removeEvents');
			$('#calendar').fullCalendar('addEventSource', holidayList); 
		}//success
	});//ajax
}
</script>
