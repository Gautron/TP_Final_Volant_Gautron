jQuery.fn.extend({
	quoteRoller : function(fn, param) {
		// all the quote roller options
		this.rollRules = $.extend({
			refreshQuoteUrl : undefined,
			nextQuoteTimeout : 10,
			startButtonSelector : '.quote-roller-start-button',
			stopButtonSelector : '.quote-roller-stop-button',
			refreshQuoteButtonSelector : '.refresh-quote-button',
			counterSelector : '.quote-roller-counter',

			quoteRollerStateSelectorClass : 'quoteRollerStarted',
			contentSelector : undefined,
			authorSelector : undefined,
			enableBtn : function(itemToEnable) {
			},
			disableBtn : function(itemToDisable) {
			}
		// events
		}, param ? param : fn);

		this.rollRules.quoteRollerStateSelector = '.'
				+ this.rollRules.quoteRollerStateSelectorClass;

		// init the usefull variables
		var rules = this.rollRules;
		var quoteRoller = this;
		this.secondBeforeNextQuote = rules.nextQuoteTimeout;

		// append the hidden only to work
		this.append('<input type="hidden" class="'
				+ rules.quoteRollerStateSelectorClass + '">');
		// we ensure that stop button is disabled
		this.rollRules.disableBtn(select(rules.stopButtonSelector));

		this.refreshNextSecond = function() {
			if (isStarted()) {
				setTimeout(refresh, 1000);
			}
		};

		this.stop = function() {
			if (isStarted()) {
				select(rules.quoteRollerStateSelector).val('false');
				rules.disableBtn(select(rules.stopButtonSelector));
				rules.enableBtn(select(rules.refreshQuoteButtonSelector));
				rules.enableBtn(select(rules.startButtonSelector));

			}
		};

		this.start = function() {
			if (isStarted() == false) {
				select(rules.quoteRollerStateSelector).val('true');
				rules.enableBtn(select(rules.stopButtonSelector));
				rules.disableBtn(select(rules.startButtonSelector));
				rules.disableBtn(select(rules.refreshQuoteButtonSelector));
				this.refreshNextSecond();
			}
		};

		// On abonne le bouton start
		select(rules.startButtonSelector).click(function() {
			quoteRoller.start();
		});

		select(rules.stopButtonSelector).click(function() {
			quoteRoller.stop();
		});

		function isStarted() {
			return select(rules.quoteRollerStateSelector).val() == 'true';
		}

		select(rules.refreshQuoteButtonSelector).click(function() {
			if (isStarted() == false) {
				refreshQuote();
			}
		});

		// just a shortcut to select element in quoteRoller
		function select(selector) {
			return quoteRoller.find(selector);
		}
		// refresh method to go to next quote if necessary
		function refresh() {
			$(rules.counterSelector).text(quoteRoller.secondBeforeNextQuote);

			if (quoteRoller.secondBeforeNextQuote == 0) {
				quoteRoller.secondBeforeNextQuote = 10;
				refreshQuote();
			} else {
				quoteRoller.secondBeforeNextQuote--;
			}
			if (isStarted()) {
				quoteRoller.refreshNextSecond();
			}
		}
		// the ajax call to the next quote
		function refreshQuote() {
			$.ajax({
				url : rules.refreshQuoteUrl,
				dataType : 'json',
				success : function(data) {
					$(rules.contentSelector).text(data.contenu);
					$(rules.authorSelector).text(data.auteur);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('error ' + errorThrown);
				}
			});
		}
	}
});