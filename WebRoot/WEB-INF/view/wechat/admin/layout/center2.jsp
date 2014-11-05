<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8">
	var centerPanel;
	function addTab(opts) {
		$.ajax({
			   type: "get",
			   url: opts.src,
			   dataType: "html",
			   success: function(html){
				   centerPanel.panel({
						fit: true,
						title: opts.title,
						content: html
					});
			   }
			});
	};
	$(function() {
		 centerPanel = $("#center-panel").panel();
	});
</script>
<div id="center-panel" class="easyui-panel">  
	
</div>
