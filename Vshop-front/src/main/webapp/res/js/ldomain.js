var domain=window.location.host;var title=$("title").text();if(domain.indexOf("www")>-1){var args={"domain":domain,"title":title};$.post("http://b2b2c.leimingtech.com/leimingtech-front/ctest",args,function(data){if("success"==data){}else{}});};