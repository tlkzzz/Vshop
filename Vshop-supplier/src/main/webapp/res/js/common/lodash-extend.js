/*************************
 * @author 张与宽
 * (c) 2014-2015 
 */
;(function(_) {
	var toString = Object.prototype.toString, nativeIsArray = Array.isArray;
	_.mixin({
		loop: function(obj, iterator, context){
			if(toString.call(obj) === '[object Number]'){
				for(var i = 0; i < obj; i++){iterator(i)}
			}else{
				_.each(obj, iterator, context);
			} 
			return obj;
		},
		copy: function(obj, isDeep){
			if (!_.isObject(obj)) return obj;
			var Obj;
			if(!isDeep){
				Obj = _.clone(obj);
			}else{
				if(toString.call(obj) === '[object Object]'){
					(Obj = {}) && _.each(obj, function(_obj, key, list){Obj[key] = typeof _obj == 'object' ? _.copy(_obj, isDeep) : _obj});
				}else if(nativeIsArray || toString.call(obj) === '[object Array]'){
					(Obj = []) && _.each(obj, function(_obj, index, list){Obj[index] = typeof _obj == 'object' ? _.copy(_obj, isDeep) : _obj});
				}
			}
			return Obj;
		},
		templateToHtml: function(templateString, data, setting){
			return _.template(templateString, !setting ? {variable: 'data'} : _.extendOwn(setting, {variable: 'data'}))(data);
		},
		format: function(str, agr){
			var isArray = !_.isUndefined(agr) && _.isArray(agr), params = isArray ? _.copy(agr, true) : _.toArray(arguments);
			!isArray && params.shift();
			_.each(str.match(/\{\d+\}/g), function(placeholder){
				var idx = parseInt(placeholder.match(/\d+/)[0]), param = params[idx], target = _.isUndefined(param) ? '' : param;
				str = str.replace(placeholder, target);
			});
			return str;
		}
	});
}(_));