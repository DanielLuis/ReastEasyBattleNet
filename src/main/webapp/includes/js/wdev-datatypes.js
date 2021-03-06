/**
 * Transforma os campos que possuï¿½rem os atributos abaixo:
 *    data-type="text" - Para campos contendo letras e nï¿½meros, bloqueando os caracteres especiais.
 *    data-type="integer" - Para campos contendo apenas nï¿½meros. 
 *    data-type="currency" - Para campos contendo valores monetï¿½rios que podem ser positivos(+), negativos(-) e conter separador de milhar(.) e decimais(,).
 */
function inicializarPluginDataTypes() {
	var padrao = {
		'currency': /^[+-]?[0-9]{1,3}(?:\.?[0-9]{3})*(?:\,[0-9]+)?$/
		, 'integer': /^\d*?$/
		, 'text': /^[\dA-Za-z]*$/
	};
	
	$('input[data-type]').each(function() {
		var input = $(this);
		var pattern = input.attr('data-type');
		input.bind('keypress', function(e) {
			var letter = null;
			if (event.which == null) { // Referï¿½ncia http://unixpapa.com/js/key.html
				letter = String.fromCharCode(event.keyCode);    // old IE
			} else if (event.which != 0 && event.charCode != 0) {
				letter = String.fromCharCode(event.which);	  // All others
			}
			 
			if (!padrao[pattern].test(letter)) {
				// se o campo for currency, permite os caracteres ",.-"
				if (pattern == 'currency') {
					if (letter == ',') {
						// nï¿½o permite mais de um caracter ,
						if (input.val().indexOf(',') == -1) { 
							return true;
						}
					} else if (letter == '.') { 
						// permite quantos separadores de milhar forem necessï¿½rios
						return true;
					} else if (letter == '-') { 
						// permite valores negativos
						if (input.val().indexOf('-') == -1) {
							return true;
						}
					}
				}
				e.preventDefault();
				return false;
			}
		});
		
		// Apaga o valor digitado caso nï¿½o esteja valido
		input.bind('blur', function() {
			var original = input.val();
			// nï¿½o faz nada se estiver vazio
			if (original != "") {
				// remove os separadores de milhar para evitar erros desnecessarios (ATENCAO: verificar se realmente E interessante fazer isso)
				var value = $.trim(original).replace(/\./g, '');
				// verifica se a expressï¿½o estï¿½ no formato monetï¿½rio correto
				var valid = padrao[pattern].test(value);
				if (valid) {
					// se o valor tratado estiver valido mas for diferente do valor digitado pelo usuario
					if (original != value) {
						// altera o campo e coloca o valor tratado
						input.val(value);
					}
				} else {
					// apagar se estiver invalido
					input.val("");
				}
			}
		});
	});
	
	// Opcoes default
	$.extend($.inputmask.defaults, {
		autoUnmask: true
		, clearIncomplete: true
		, showMaskOnHover: false
	});

	var mascaras = {
		'data': {mask: '99/99/9999', autoUnmask: false}
		, 'cpf': {mask: '999.999.999-99'}
		, 'cep': {mask: '99999-999'}
		, 'telefone': {mask: '(99) 9999-9999'}
		, 'celular': {mask: '(99) 9999-9999'}
		, 'celular-nono-digito': {mask: '(99) 99999-9999'}
	};
	
	function boolean(value, defaultValue) {
		if (value == null) {
			return defaultValue || false;
		}
		return ($.trim(value) === "true" || $.trim(value) === "yes" || value === true);
	}
	
	$('input[data-mask]').each(function() {
		var $input = $(this);
		var mascara = $input.attr('data-mask');
		
		var opcoesMascara = mascaras[mascara]; 
		
		if (!opcoesMascara) {
			opcoesMascara = {mask: mascara, autoUnmask: boolean($input.attr('data-autounmask'), true) };
		}
		
		if(mascara == 'celular' && $.trim($input.val()) != '') {
			if($input.val().length > 8) {
				opcoesMascara = mascaras['celular-nono-digito'];
			}
		}
		
		$input.inputmask(opcoesMascara.mask, opcoesMascara);
		
		if(!$input.inputmask("isComplete")) {
			$input.val('');
		}
		
		$input.bind('keyup', function(){
			if(mascara == 'celular') {
				var value = $input.val();
				var ddd = "";
				
				if(value.length >= 2){
					ddd = value.substring(0,2);
				}
				
				if(ddd == "11"){
					opcoesMascara = mascaras['celular-nono-digito'];
					$input.inputmask(opcoesMascara.mask, opcoesMascara);
				} else {
					opcoesMascara = mascaras['celular'];
					$input.inputmask(opcoesMascara.mask, opcoesMascara);
				}
			}
		});
	});
	
	function right(value, size) {
		return value.substring(value.length - size);
	}
	
	function format(pattern, value) {
		return pattern.replace("dd", right("00" + value.getDate(), 2))
					  .replace("mm", right("00" + (value.getMonth() + 1), 2))
					  .replace("yy", right("00" + value.getFullYear(), 4));
	}
	
	var dateTypes = {
		'botao-calendario': {showOn: 'button', buttonImage: 'css/images/calendar.gif', buttonImageOnly: true}
		, 'nascimento': {yearRange: 'c-100:c+50'} // Data de nascimento (-100 anos / +50 anos a partir da data atual)
		, 'pesquisa-futura': {yearRange: 'c-100:c+10'} // Pesquisa futura (-100 anos / +10 anos a partir da data atual)
	};

	$('input[data-date-type]').each(function() {
		var input$ = $(this);
		var dateType = input$.attr('data-date-type') || 'default';
		
		var dateConfig = { showButtonPanel: false, changeMonth: true, changeYear: true, onClose: function() {
			var date = input$.datepicker("getDate");
			if (date == null 
				  // contornar o bug do jQuery UI http://bugs.jqueryui.com/ticket/8009
				  || $.trim(input$.val()) != $.datepicker.formatDate(input$.datepicker("option", "dateFormat"), date)
				) {
				input$.val("");
			}
		}};
		
		// fix para o bug do enter nï¿½o validar uma data invï¿½lida
		input$.blur(function() {
			var date = null;
			try {
				date = $.datepicker.parseDate(input$.datepicker("option", "dateFormat"), input$.val());
			} catch (e) {}
			if (date == null) {
				input$.val("");
			}
		});
		
		$.extend(dateConfig, dateTypes[dateType]);
		
		switch (dateType) {
			case "nascimento":
				$.extend(dateConfig, {});
				break;
			case "pesquisa-futura":
				$.extend(dateConfig, {changeMonth: true, changeYear: true, showButtonPanel: false, });
				break;
		}
					
		input$.datepicker(dateConfig);
		
		input$.keypress(function(event) {
			var keyCode = null;
			
			if (event.which == null) {
				keyCode = event.keyCode;
			} else if (event.which != 0) {
				keyCode = event.which;
			} else if (event.charCode != 0) {
				keyCode = event.charCode;
			}
			
			// Bloqueia o Enter
			if(keyCode == 13) {
				event.preventDefault();
				return false;
			}
		});
    });
}



/**
 * Mï¿½TODO PARA MASCARAR OS CAMPOS TYPE FILE
 */
function mascararInputFile(){
	$("input[type=file]").each(function(){
		var input = $(this);
		var name = input.attr('name');
		var nameFake = name+"Fake";
		var value = input.val();
		var inputFake = "<input name='"+nameFake+"' value='"+value+"' /><a href='#' onclick='return false;' id='"+nameFake+"'><img src='css/images/folder.png' height='20' /></a>";
		
		input.attr('onkeypress', 'return false;');
		input.bind('keypress', function(e){
			e.preventDefault();
			return false;
		}).bind('keydown', function(){
			return false;
		});
		
		input.after(inputFake);
		input.hide();
		
		input.bind('change', function(){
			$("input[name='"+nameFake+"']").val($(this).val());
		});
		
		$("input[name='"+nameFake+"'], a#"+nameFake+" ").bind('click', function(){
			input.click();
		});
		
		$("input[name='"+nameFake+"']").attr('onkeypress', 'return false;');
		$("input[name='"+nameFake+"']").bind('keypress', function(e){
			e.preventDefault();
			return false;
		}).bind('keydown', function(){
			return false;
		});
	});
}


$(document).ready(function(){
	inicializarPluginDataTypes();
	
	mascararInputFile();
});