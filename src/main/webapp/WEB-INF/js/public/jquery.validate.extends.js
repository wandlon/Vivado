//验证手机号码
jQuery.validator.addMethod("telephone", function(value, element) {   
    var tel = /^1\d{10}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写手机号码");

//验证手机IMEI
jQuery.validator.addMethod("imei", function(value, element) {   
    var tel = /^[0-9]{15}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写手机IMEI");