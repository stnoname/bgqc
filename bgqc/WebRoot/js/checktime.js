/**
 * 
 */
function checktime(obj){
	if(obj.needstarttime != null && obj.needendtime != ""){
		var startTmp = obj.needstarttime.replace(" ", ":").replace(/\:/g,"-").split("-");
		var endTmp = obj.needendtime.replace(" ", ":").replace(/\:/g,"-").split("-");
		var sd = new Date(startTmp[0],startTmp[1],startTmp[2],startTmp[3],startTmp[4],startTmp[5]);
		var ed = new Date(endTmp[0],endTmp[1],endTmp[2],endTmp[3],endTmp[4],endTmp[5]);
		
		if(sd.getTime() > ed.getTime()){
			alert("结束日期不能早于开始时间！");
			return false;
		}
		if(obj.needstarttime == null || obj.needstarttime == ""){
			alert("开始时间不能为空！");
			return false;
		}
		if(obj.needendtime == null || obj.needendtime == ""){
			alert("结束时间不能为空！");
			return false;
		}
		return true;
	}
}