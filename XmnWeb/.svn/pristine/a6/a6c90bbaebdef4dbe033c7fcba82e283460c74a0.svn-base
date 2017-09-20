if ("undefined" == typeof jQuery) {
	throw new Error("ZUI requires jQuery")
}
!function(d, c) {
	"function" == typeof define && define.amd ? define(["jquery"], c) : "object" == typeof exports ? module.exports = c(require("jquery")) : d.bootbox = c(d.jQuery)
}(this, function a(I, H) {
			function G() {
				var b;
				if ("undefined" != typeof config && config.clientLang) {
					b = config.clientLang
				} else {
					var d = I("html").attr("lang");
					b = d ? d : "en"
				}
				return b.replace("-", "_").toLowerCase()
			}
			function F(d) {
				var c = s[u.locale];
				return c ? c[d] : s.en[d]
			}
			function E(b, h, g) {
				b.stopPropagation(), b.preventDefault();
				var f = I.isFunction(g) && g(b) === !1;
				f || h.modal("hide")
			}
			function D(e) {
				var d, f = 0;
				for (d in e) {
					f++
				}
				return f
			}
			function C(b, f) {
				var e = 0;
				I.each(b, function(d, c) {
							f(d, c, e++)
						})
			}
			function B(b) {
				var f, e;
				if ("object" != typeof b) {
					throw new Error("Please supply an object of options")
				}
				if (!b.message) {
					throw new Error("Please specify a message")
				}
				return b = I.extend({}, u, b), b.buttons || (b.buttons = {}), b.backdrop = b.backdrop ? "static" : !1, f = b.buttons, e = D(f), C(f, function(c, d) {
							if (I.isFunction(d) && (d = f[c] = {
								callback : d
							}), "object" !== I.type(d)) {
								throw new Error("button with key " + c + " must be an object")
							}
							d.label || (d.label = c), d.className || (d.className = 1 == e || e >= 2 && "confirm" === c ? "btn-primary" : "btn-default")
						}), b
			}
			function A(f, e) {
				var h = f.length, g = {};
				if (1 > h || h > 2) {
					throw new Error("Invalid argument length")
				}
				return 2 === h || "string" == typeof f[0] ? (g[e[0]] = f[0], g[e[1]] = f[1]) : g = f[0], g
			}
			function z(b, f, e) {
				return I.extend(!0, {}, b, A(f, e))
			}
			function y(g, f, k, j) {
				var h = {
					className : "bootbox-" + g,
					buttons : x.apply(null, f)
				};
				return w(z(h, j, k), f)
			}
			function x() {
				for (var h = {}, e = 0, m = arguments.length; m > e; e++) {
					var l = arguments[e], k = l.toLowerCase(), j = l.toUpperCase();
					h[k] = {
						label : F(j)
					}
				}
				return h
			}
			function w(e, c) {
				var f = {};
				return C(c, function(g, d) {
							f[d] = !0
						}), C(e.buttons, function(b) {
							if (f[b] === H) {
								throw new Error("button key " + b + " is not allowed (options are " + c.join("\n") + ")")
							}
						}), e
			}
			var v = {
				dialog : "<div class='bootbox modal' tabindex='-1' role='dialog'><div class='modal-dialog'><div class='modal-content'><div class='modal-body'><div class='bootbox-body'></div></div></div></div></div>",
				header : "<div class='modal-header'><h4 class='modal-title'></h4></div>",
				footer : "<div class='modal-footer'></div>",
				closeButton : "<button type='button' class='bootbox-close-button close' data-dismiss='modal' aria-hidden='true'>&times;</button>",
				form : "<form class='bootbox-form'></form>",
				inputs : {
					text : "<input class='bootbox-input bootbox-input-text form-control' autocomplete=off type=text />",
					textarea : "<textarea class='bootbox-input bootbox-input-textarea form-control'></textarea>",
					email : "<input class='bootbox-input bootbox-input-email form-control' autocomplete='off' type='email' />",
					select : "<select class='bootbox-input bootbox-input-select form-control'></select>",
					checkbox : "<div class='checkbox'><label><input class='bootbox-input bootbox-input-checkbox' type='checkbox' /></label></div>",
					date : "<input class='bootbox-input bootbox-input-date form-control' autocomplete=off type='date' />",
					time : "<input class='bootbox-input bootbox-input-time form-control' autocomplete=off type='time' />",
					number : "<input class='bootbox-input bootbox-input-number form-control' autocomplete=off type='number' />",
					password : "<input class='bootbox-input bootbox-input-password form-control' autocomplete='off' type='password' />"
				}
			}, u = {
				locale : G(),
				backdrop : !0,
				animate : !0,
				className : null,
				closeButton : !0,
				show : !0,
				container : "body"
			}, t = {};
			t.alert = function() {
				var b;
				if (b = y("alert", ["ok"], ["message", "callback"], arguments), b.callback && !I.isFunction(b.callback)) {
					throw new Error("alert requires callback property to be a function when provided")
				}
				return b.buttons.ok.callback = b.onEscape = function() {
					return I.isFunction(b.callback) ? b.callback() : !0
				}, t.dialog(b)
			}, t.confirm = function() {
				var b;
				if (b = y("confirm", ["confirm", "cancel"], ["message", "callback"], arguments), b.buttons.cancel.callback = b.onEscape = function() {
					return b.callback(!1)
				}, b.buttons.confirm.callback = function() {
					return b.callback(!0)
				}, !I.isFunction(b.callback)) {
					throw new Error("confirm requires a callback")
				}
				return t.dialog(b)
			}, t.prompt = function() {
				var K, J, q, o, n, m, k;
				o = I(v.form), J = {
					className : "bootbox-prompt",
					buttons : x("confirm", "cancel"),
					value : "",
					inputType : "text"
				}, K = w(z(J, arguments, ["title", "callback"]), ["cancel", "confirm"]), m = K.show === H ? !0 : K.show;
				var h = ["date", "time", "number"], c = document.createElement("input");
				if (c.setAttribute("type", K.inputType), h[K.inputType] && (K.inputType = c.type), K.message = o, K.buttons.cancel.callback = K.onEscape = function() {
					return K.callback(null)
				}, K.buttons.confirm.callback = function() {
					var f;
					switch (K.inputType) {
						case "text" :
						case "textarea" :
						case "email" :
						case "select" :
						case "date" :
						case "time" :
						case "number" :
						case "password" :
							f = n.val();
							break;
						case "checkbox" :
							var e = n.find("input:checked");
							f = [], C(e, function(g, j) {
										f.push(I(j).val())
									})
					}
					return K.callback(f)
				}, K.show = !1, !K.title) {
					throw new Error("prompt requires a title")
				}
				if (!I.isFunction(K.callback)) {
					throw new Error("prompt requires a callback")
				}
				if (!v.inputs[K.inputType]) {
					throw new Error("invalid prompt type")
				}
				switch (n = I(v.inputs[K.inputType]), K.inputType) {
					case "text" :
					case "textarea" :
					case "email" :
					case "date" :
					case "time" :
					case "number" :
					case "password" :
						n.val(K.value);
						break;
					case "select" :
						var b = {};
						if (k = K.inputOptions || [], !k.length) {
							throw new Error("prompt with select requires options")
						}
						C(k, function(f, j) {
									var g = n;
									if (j.value === H || j.text === H) {
										throw new Error("given options in wrong format")
									}
									j.group && (b[j.group] || (b[j.group] = I("<optgroup/>").attr("label", j.group)), g = b[j.group]), g.append("<option value='" + j.value + "'>" + j.text + "</option>")
								}), C(b, function(e, d) {
									n.append(d)
								}), n.val(K.value);
						break;
					case "checkbox" :
						var L = I.isArray(K.value) ? K.value : [K.value];
						if (k = K.inputOptions || [], !k.length) {
							throw new Error("prompt with checkbox requires options")
						}
						if (!k[0].value || !k[0].text) {
							throw new Error("given options in wrong format")
						}
						n = I("<div/>"), C(k, function(j, g) {
									var f = I(v.inputs[K.inputType]);
									f.find("input").attr("value", g.value), f.find("label").append(g.text), C(L, function(e, d) {
												d === g.value && f.find("input").prop("checked", !0)
											}), n.append(f)
								})
				}
				return K.placeholder && n.attr("placeholder", K.placeholder), K.pattern && n.attr("pattern", K.pattern), o.append(n), o.on("submit", function(d) {
							d.preventDefault(), d.stopPropagation(), q.find(".btn-primary").click()
						}), q = t.dialog(K), q.off("shown.bs.modal"), q.on("shown.bs.modal", function() {
							n.focus()
						}), m === !0 && q.modal("show"), q
			}, t.dialog = function(f) {
				f = B(f);
				var q = I(v.dialog), p = q.find(".modal-dialog"), o = q.find(".modal-body"), n = f.buttons, m = "", h = {
					onEscape : f.onEscape
				};
				if (C(n, function(d, c) {
							m += "<button data-bb-handler='" + d + "' type='button' class='btn " + c.className + "'>" + c.label + "</button>", h[d] = c.callback
						}), o.find(".bootbox-body").html(f.message), f.animate === !0 && q.addClass("fade"), f.className && q.addClass(f.className), "large" === f.size && p.addClass("modal-lg"), "small" === f.size && p.addClass("modal-sm"), f.title && o.before(v.header), f.closeButton) {
					var b = I(v.closeButton);
					f.title ? q.find(".modal-header").prepend(b) : b.css("margin-top", "-10px").prependTo(o)
				}
				return f.title && q.find(".modal-title").html(f.title), m.length && (o.after(v.footer), q.find(".modal-footer").html(m)), q.on("hidden.bs.modal", function(c) {
							c.target === this && q.remove()
						}), q.on("shown.bs.modal", function() {
							q.find(".btn-primary:first").focus()
						}), q.on("escape.close.bb", function(c) {
							h.onEscape && E(c, q, h.onEscape)
						}), q.on("click", ".modal-footer button", function(c) {
							var e = I(this).data("bb-handler");
							E(c, q, h[e])
						}), q.on("click", ".bootbox-close-button", function(c) {
							E(c, q, h.onEscape)
						}), q.on("keyup", function(c) {
							27 === c.which && q.trigger("escape.close.bb")
						}), I(f.container).append(q), q.modal({
							backdrop : f.backdrop,
							keyboard : !1,
							show : !1
						}), f.show && q.modal("show"), q
			}, t.setDefaults = function() {
				var b = {};
				2 === arguments.length ? b[arguments[0]] = arguments[1] : b = arguments[0], I.extend(u, b)
			}, t.hideAll = function() {
				I(".bootbox").modal("hide")
			};
			var s = {
				en : {
					OK : "OK",
					CANCEL : "Cancel",
					CONFIRM : "OK"
				},
				zh_cn : {
					OK : "好的",
					CANCEL : "取消",
					CONFIRM : "确认"
				},
				zh_tw : {
					OK : "好的",
					CANCEL : "取消",
					CONFIRM : "確認"
				}
			};
			return t.init = function(b) {
				return a(b || I)
			}, t
		}), String.prototype.format = function(g) {
	var f = this;
	if (arguments.length > 0) {
		var k;
		if (1 == arguments.length && "object" == typeof g) {
			for (var j in g) {
				void 0 != g[j] && (k = new RegExp("({" + j + "})", "g"), f = f.replace(k, g[j]))
			}
		} else {
			for (var h = 0; h < arguments.length; h++) {
				void 0 != arguments[h] && (k = new RegExp("({[" + h + "]})", "g"), f = f.replace(k, arguments[h]))
			}
		}
	}
	return f
}, String.prototype.isNum = function(e) {
	if (null != e) {
		var d, f;
		return f = /\d*/i, d = e.match(f), d == e ? !0 : !1
	}
	return !1
} + function(x, w, v) {
	function u() {
		t = w[q](function() {
					s.each(function() {
								var f = x(this), j = f.width(), h = f.height(), g = x.data(this, o);
								(j !== g.w || h !== g.h) && f.trigger(p, [g.w = j, g.h = h])
							}), u()
				}, r[n])
	}
	var t, s = x([]), r = x.resize = x.extend(x.resize, {}), q = "setTimeout", p = "resize", o = p + "-special-event", n = "delay", m = "throttleWindow";
	r[n] = 250, r[m] = !0, x.event.special[p] = {
		setup : function() {
			if (!r[m] && this[q]) {
				return !1
			}
			var c = x(this);
			s = s.add(c), x.data(this, o, {
						w : c.width(),
						h : c.height()
					}), 1 === s.length && u()
		},
		teardown : function() {
			if (!r[m] && this[q]) {
				return !1
			}
			var c = x(this);
			s = s.not(c), c.removeData(o), s.length || clearTimeout(t)
		},
		add : function(c) {
			function g(e, y, l) {
				var k = x(this), j = x.data(this, o);
				j.w = y !== v ? y : k.width(), j.h = l !== v ? l : k.height(), f.apply(this, arguments)
			}
			if (!r[m] && this[q]) {
				return !1
			}
			var f;
			return x.isFunction(c) ? (f = c, g) : (f = c.handler, void(c.handler = g))
		}
	}
}(jQuery, this), +function(f, e, h, g) {
	f.extend({
				uuid : function() {
					for (var b = (new Date).getTime(); 10000000000000000 > b;) {
						b *= 10
					}
					return b + g.floor(9999 * g.random())
				},
				getPropertyCount : function(b) {
					return "object" != typeof b || null == b ? 0 : Object.getOwnPropertyNames(b).length
				},
				callEvent : function(j, l, k) {
					return f.isFunction(j) ? ("undefined" != typeof k && (j = f.proxy(j, k)), l.result = j(l), !(void 0 != l.result && !l.result)) : 1
				},
				clientLang : function() {
					var j;
					if ("undefined" != typeof e.config && e.config.clientLang) {
						j = e.config.clientLang
					} else {
						var b = f("html").attr("lang");
						j = b ? b : navigator.userLanguage || navigator.userLanguage || "zh_cn"
					}
					return j.replace("-", "_").toLowerCase()
				}
			}), f.fn.callEvent = function(j, q, p) {
		var o = f(this), n = j.indexOf(".zui."), m = j;
		0 > n && p && p.name ? j += "." + p.name : m = j.substring(0, n);
		var l = f.Event(j, q);
		o.trigger(l);
		if ("undefined" == typeof p && n > 0 && (p = o.data(j.substring(n + 1))), p && p.options) {
			var k = p.options[m];
			f.isFunction(k) && f.callEvent(p.options[m], l, p)
		}
		return l
	}
}(jQuery, window, document, Math), Date.prototype.format = function(e) {
	var d = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	/(y+)/i.test(e) && (e = e.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)));
	for (var f in d) {
		new RegExp("(" + f + ")").test(e) && (e = e.replace(RegExp.$1, 1 == RegExp.$1.length ? d[f] : ("00" + d[f]).substr(("" + d[f]).length)))
	}
	return e
}, Date.prototype.addMilliseconds = function(b) {
	return this.setTime(this.getTime() + b), this
}, Date.prototype.addDays = function(b) {
	return this.addMilliseconds(24 * b * 3600 * 1000), this
}, Date.prototype.clone = function() {
	var b = new Date;
	return b.setTime(this.getTime()), b
}, Date.isLeapYear = function(b) {
	return b % 4 === 0 && b % 100 !== 0 || b % 400 === 0
}, Date.getDaysInMonth = function(d, c) {
	return [31, Date.isLeapYear(d) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][c]
}, Date.prototype.isLeapYear = function() {
	var b = this.getFullYear();
	return b % 4 === 0 && b % 100 !== 0 || b % 400 === 0
}, Date.prototype.getDaysInMonth = function() {
	return Date.getDaysInMonth(this.getFullYear(), this.getMonth())
}, Date.prototype.addMonths = function(d) {
	var c = this.getDate();
	return this.setDate(1), this.setMonth(this.getMonth() + d), this.setDate(Math.min(c, this.getDaysInMonth())), this
}, +function(k, j) {
	var q = "localStorage", p = k[q], o = k.store, n = "page_" + k.location.pathname, m = function() {
		this.slience = !0, this.enable = q in k && k[q] && k[q].setItem, this.storage = p;
		this.page = this.get(n, {})
	};
	m.prototype.pageSave = function() {
		if (j.isEmptyObject(this.page)) {
			this.remove(n)
		} else {
			for (var b in this.page) {
				var d = this.page[b];
				null === d && delete this.page[b]
			}
			this.set(n, this.page)
		}
	}, m.prototype.pageRemove = function(b) {
		"undefined" != typeof this.page[b] && (this.page[b] = null, this.pageSave())
	}, m.prototype.pageClear = function() {
		this.page = {}, this.pageSave()
	}, m.prototype.pageGet = function(e, d) {
		var f = this.page[e];
		return void 0 === d || null !== f && void 0 !== f ? f : d
	}, m.prototype.pageSet = function(b, d) {
		j.isPlainObject(b) ? j.extend(!0, this.page, b) : this.page[this.serialize(b)] = d, this.pageSave()
	}, m.prototype.check = function() {
		if (!this.enable && !this.slience) {
			throw new Error("Browser not support localStorage or enable status been set true.")
		}
		return this.enable
	}, m.prototype.length = function() {
		return this.check() ? p.length : 0
	}, m.prototype.removeItem = function(b) {
		p.removeItem(b)
	}, m.prototype.remove = function(b) {
		this.removeItem(b)
	}, m.prototype.getItem = function(b) {
		return p.getItem(b)
	}, m.prototype.get = function(e, d) {
		var f = this.deserialize(this.getItem(e));
		return void 0 === d || null !== f && void 0 !== f ? f : d
	}, m.prototype.key = function() {
		return p.key(key)
	}, m.prototype.setItem = function(d, c) {
		p.setItem(d, c)
	}, m.prototype.set = function(d, c) {
		return void 0 === c ? this.remove(d) : void this.setItem(d, this.serialize(c))
	}, m.prototype.clear = function() {
		p.clear()
	}, m.prototype.forEach = function(e) {
		for (var d = 0; d < p.length; d++) {
			var f = p.key(d);
			e(f, l.get(f))
		}
	}, m.prototype.getAll = function() {
		var b = {};
		return this.forEach(function(d, e) {
					b[d] = e
				}), b
	}, m.prototype.serialize = function(b) {
		return "string" == typeof b ? b : JSON.stringify(b)
	}, m.prototype.deserialize = function(d) {
		if ("string" != typeof d) {
			return void 0
		}
		try {
			return JSON.parse(d)
		} catch (c) {
			return d || void 0
		}
	};
	var l = new m;
	k.store = l, k.store.noConflict = function() {
		return k.store = o, l
	}
}(window, jQuery), +function(d) {
	function c() {
		var f = document.createElement("bootstrap"), e = {
			WebkitTransition : "webkitTransitionEnd",
			MozTransition : "transitionend",
			transition : "transitionend"
		};
		for (var g in e) {
			if (void 0 !== f.style[g]) {
				return {
					end : e[g]
				}
			}
		}
	}
	d.fn.emulateTransitionEnd = function(f) {
		var j = !1, h = this;
		d(this).one(d.support.transition.end, function() {
					j = !0
				});
		var g = function() {
			j || d(h).trigger(d.support.transition.end)
		};
		return setTimeout(g, f), this
	}, d(function() {
				d.support.transition = c()
			})
}(jQuery), +function(f) {
	var e = '[data-dismiss="alert"]', h = function(b) {
		f(b).on("click", e, this.close)
	};
	h.prototype.close = function(j) {
		function n() {
			k.trigger("closed.bs.alert").remove()
		}
		var m = f(this), l = m.attr("data-target");
		l || (l = m.attr("href"), l = l && l.replace(/.*(?=#[^\s]*$)/, ""));
		var k = f(l);
		j && j.preventDefault(), k.length || (k = m.hasClass("alert") ? m : m.parent()), k.trigger(j = f.Event("close.bs.alert")), j.isDefaultPrevented() || (k.removeClass("in"), f.support.transition && k.hasClass("fade") ? k.one(f.support.transition.end, n).emulateTransitionEnd(150) : n())
	};
	var g = f.fn.alert;
	f.fn.alert = function(c) {
		return this.each(function() {
					var j = f(this), b = j.data("bs.alert");
					b || j.data("bs.alert", b = new h(this)), "string" == typeof c && b[c].call(j)
				})
	}, f.fn.alert.Constructor = h, f.fn.alert.noConflict = function() {
		return f.fn.alert = g, this
	}, f(document).on("click.bs.alert.data-api", e, h.prototype.close)
}(window.jQuery), +function(e) {
	var d = function(g, b) {
		this.$element = e(g), this.options = e.extend({}, d.DEFAULTS, b), this.transitioning = null, this.options.parent && (this.$parent = e(this.options.parent)), this.options.toggle && this.toggle()
	};
	d.DEFAULTS = {
		toggle : !0
	}, d.prototype.dimension = function() {
		var b = this.$element.hasClass("width");
		return b ? "width" : "height"
	}, d.prototype.show = function() {
		if (!this.transitioning && !this.$element.hasClass("in")) {
			var h = e.Event("show.bs.collapse");
			if (this.$element.trigger(h), !h.isDefaultPrevented()) {
				var n = this.$parent && this.$parent.find("> .panel > .in");
				if (n && n.length) {
					var m = n.data("bs.collapse");
					if (m && m.transitioning) {
						return
					}
					n.collapse("hide"), m || n.data("bs.collapse", null)
				}
				var l = this.dimension();
				this.$element.removeClass("collapse").addClass("collapsing")[l](0), this.transitioning = 1;
				var k = function() {
					this.$element.removeClass("collapsing").addClass("in")[l]("auto"), this.transitioning = 0, this.$element.trigger("shown.bs.collapse")
				};
				if (!e.support.transition) {
					return k.call(this)
				}
				var j = e.camelCase(["scroll", l].join("-"));
				this.$element.one(e.support.transition.end, e.proxy(k, this)).emulateTransitionEnd(350)[l](this.$element[0][j])
			}
		}
	}, d.prototype.hide = function() {
		if (!this.transitioning && this.$element.hasClass("in")) {
			var g = e.Event("hide.bs.collapse");
			if (this.$element.trigger(g), !g.isDefaultPrevented()) {
				var j = this.dimension();
				this.$element[j](this.$element[j]())[0].offsetHeight, this.$element.addClass("collapsing").removeClass("collapse").removeClass("in"), this.transitioning = 1;
				var h = function() {
					this.transitioning = 0, this.$element.trigger("hidden.bs.collapse").removeClass("collapsing").addClass("collapse")
				};
				return e.support.transition ? void this.$element[j](0).one(e.support.transition.end, e.proxy(h, this)).emulateTransitionEnd(350) : h.call(this)
			}
		}
	}, d.prototype.toggle = function() {
		this[this.$element.hasClass("in") ? "hide" : "show"]()
	};
	var f = e.fn.collapse;
	e.fn.collapse = function(b) {
		return this.each(function() {
					var h = e(this), g = h.data("bs.collapse"), c = e.extend({}, d.DEFAULTS, h.data(), "object" == typeof b && b);
					g || h.data("bs.collapse", g = new d(this, c)), "string" == typeof b && g[b]()
				})
	}, e.fn.collapse.Constructor = d, e.fn.collapse.noConflict = function() {
		return e.fn.collapse = f, this
	}, e(document).on("click.bs.collapse.data-api", "[data-toggle=collapse]", function(s) {
				var r, q = e(this), p = q.attr("data-target") || s.preventDefault() || (r = q.attr("href")) && r.replace(/.*(?=#[^\s]+$)/, ""), o = e(p), n = o.data("bs.collapse"), m = n ? "toggle" : q.data(), l = q.attr("data-parent"), k = l && e(l);
				n && n.transitioning || (k && k.find('[data-toggle=collapse][data-parent="' + l + '"]').not(q).addClass("collapsed"), q[o.hasClass("in") ? "addClass" : "removeClass"]("collapsed")), o.collapse(m)
			})
}(window.jQuery), +function(j) {
	function h() {
		j(n).remove(), j(m).each(function(c) {
					var e = o(j(this));
					e.hasClass("open") && (e.trigger(c = j.Event("hide.bs.dropdown")), c.isDefaultPrevented() || e.removeClass("open").trigger("hidden.bs.dropdown"))
				})
	}
	function o(e) {
		var g = e.attr("data-target");
		g || (g = e.attr("href"), g = g && /#/.test(g) && g.replace(/.*(?=#[^\s]*$)/, ""));
		var f = g && j(g);
		return f && f.length ? f : e.parent()
	}
	var n = ".dropdown-backdrop", m = "[data-toggle=dropdown]", l = function(c) {
		j(c).on("click.bs.dropdown", this.toggle)
	};
	l.prototype.toggle = function(q) {
		var p = j(this);
		if (!p.is(".disabled, :disabled")) {
			var c = o(p), b = c.hasClass("open");
			if (h(), !b) {
				if ("ontouchstart" in document.documentElement && !c.closest(".navbar-nav").length && j('<div class="dropdown-backdrop"/>').insertAfter(j(this)).on("click", h), c.trigger(q = j.Event("show.bs.dropdown")), q.isDefaultPrevented()) {
					return
				}
				c.toggleClass("open").trigger("shown.bs.dropdown"), p.focus()
			}
			return !1
		}
	}, l.prototype.keydown = function(c) {
		if (/(38|40|27)/.test(c.keyCode)) {
			var s = j(this);
			if (c.preventDefault(), c.stopPropagation(), !s.is(".disabled, :disabled")) {
				var r = o(s), q = r.hasClass("open");
				if (!q || q && 27 == c.keyCode) {
					return 27 == c.which && r.find(m).focus(), s.click()
				}
				var p = j("[role=menu] li:not(.divider):visible a", r);
				if (p.length) {
					var e = p.index(p.filter(":focus"));
					38 == c.keyCode && e > 0 && e--, 40 == c.keyCode && e < p.length - 1 && e++, ~e || (e = 0), p.eq(e).focus()
				}
			}
		}
	};
	var k = j.fn.dropdown;
	j.fn.dropdown = function(c) {
		return this.each(function() {
					var e = j(this), b = e.data("dropdown");
					b || e.data("dropdown", b = new l(this)), "string" == typeof c && b[c].call(e)
				})
	}, j.fn.dropdown.Constructor = l, j.fn.dropdown.noConflict = function() {
		return j.fn.dropdown = k, this
	}, j(document).on("click.bs.dropdown.data-api", h).on("click.bs.dropdown.data-api", ".dropdown form", function(b) {
				b.stopPropagation()
			}).on("click.bs.dropdown.data-api", m, l.prototype.toggle).on("keydown.bs.dropdown.data-api", m + ", [role=menu]", l.prototype.keydown)
}(window.jQuery), +function(e) {
	var d = function(g, h) {
		this.options = h, this.$element = e(g), this.$backdrop = this.isShown = null, this.options.remote && this.$element.load(this.options.remote)
	};
	d.DEFAULTS = {
		backdrop : !0,
		keyboard : !0,
		show : !0,
		position : "fit"
	}, d.prototype.toggle = function(b) {
		return this[this.isShown ? "hide" : "show"](b)
	}, d.prototype.show = function(g) {
		var j = this, h = e.Event("show.bs.modal", {
					relatedTarget : g
				});
		this.$element.trigger(h), this.isShown || h.isDefaultPrevented() || (this.isShown = !0, this.escape(), this.$element.on("click.dismiss.modal", '[data-dismiss="modal"]', e.proxy(this.hide, this)), this.backdrop(function() {
					var m = e.support.transition && j.$element.hasClass("fade");
					if (j.$element.parent().length || j.$element.appendTo(document.body), j.$element.show(), m && j.$element[0].offsetWidth, j.$element.addClass("in").attr("aria-hidden", !1), j.options.position) {
						var l = j.$element.find(".modal-dialog"), k = Math.max(0, (e(window).height() - l.outerHeight()) / 2), c = "fit" == j.options.position ? 2 * k / 3 : "center" == j.options.position ? k : j.options.position;
						l.css("margin-top", c)
					}
					j.enforceFocus();
					var b = e.Event("shown.bs.modal", {
								relatedTarget : g
							});
					m ? j.$element.find(".modal-dialog").one(e.support.transition.end, function() {
								j.$element.focus().trigger(b)
							}).emulateTransitionEnd(300) : j.$element.focus().trigger(b)
				}))
	}, d.prototype.hide = function(c) {
		c && c.preventDefault(), c = e.Event("hide.bs.modal"), this.$element.trigger(c), this.isShown && !c.isDefaultPrevented() && (this.isShown = !1, this.escape(), e(document).off("focusin.bs.modal"), this.$element.removeClass("in").attr("aria-hidden", !0).off("click.dismiss.modal"), e.support.transition && this.$element.hasClass("fade") ? this.$element.one(e.support.transition.end, e.proxy(this.hideModal, this)).emulateTransitionEnd(300) : this.hideModal())
	}, d.prototype.enforceFocus = function() {
		e(document).off("focusin.bs.modal").on("focusin.bs.modal", e.proxy(function(b) {
							this.$element[0] === b.target || this.$element.has(b.target).length || this.$element.focus()
						}, this))
	}, d.prototype.escape = function() {
		this.isShown && this.options.keyboard ? e(document).on("keyup.dismiss.bs.modal", e.proxy(function(g) {
							if (27 == g.which) {
								var j = e.Event("escaping.bs.modal"), h = this.$element.triggerHandler(j, "esc");
								if (void 0 != h && !h) {
									return
								}
								this.hide()
							}
						}, this)) : this.isShown || e(document).off("keyup.dismiss.bs.modal")
	}, d.prototype.hideModal = function() {
		var b = this;
		this.$element.hide(), this.backdrop(function() {
					b.removeBackdrop(), b.$element.trigger("hidden.bs.modal")
				})
	}, d.prototype.removeBackdrop = function() {
		this.$backdrop && this.$backdrop.remove(), this.$backdrop = null
	}, d.prototype.backdrop = function(g) {
		var j = this.$element.hasClass("fade") ? "fade" : "";
		if (this.isShown && this.options.backdrop) {
			var h = e.support.transition && j;
			if (this.$backdrop = e('<div class="modal-backdrop ' + j + '" />').appendTo(document.body), this.$element.on("click.dismiss.modal", e.proxy(function(b) {
								b.target === b.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this))
							}, this)), h && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !g) {
				return
			}
			h ? this.$backdrop.one(e.support.transition.end, g).emulateTransitionEnd(150) : g()
		} else {
			!this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"), e.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one(e.support.transition.end, g).emulateTransitionEnd(150) : g()) : g && g()
		}
	};
	var f = e.fn.modal;
	e.fn.modal = function(g, b) {
		return this.each(function() {
					var j = e(this), h = j.data("bs.modal"), c = e.extend({}, d.DEFAULTS, j.data(), "object" == typeof g && g);
					h || j.data("bs.modal", h = new d(this, c)), "string" == typeof g ? h[g](b) : c.show && h.show(b)
				})
	}, e.fn.modal.Constructor = d, e.fn.modal.noConflict = function() {
		return e.fn.modal = f, this
	}, e(document).on("click.bs.modal.data-api", '[data-toggle="modal"]', function(g) {
				var l = e(this), k = l.attr("href"), j = e(l.attr("data-target") || k && k.replace(/.*(?=#[^\s]+$)/, ""));
				if (!(j.length < 1)) {
					var h = j.data("modal") ? "toggle" : e.extend({
								remote : !/#/.test(k) && k
							}, j.data(), l.data());
					g.preventDefault(), j.modal(h, this).one("hide", function() {
								l.is(":visible") && l.focus()
							})
				}
			}), e(document).on("show.bs.modal", ".modal", function() {
				e(document.body).addClass("modal-open")
			}).on("hidden.bs.modal", ".modal", function() {
				e(document.body).removeClass("modal-open")
			})
}(window.jQuery), +function(e) {
	var d = function(g, c) {
		this.type = this.options = this.enabled = this.timeout = this.hoverState = this.$element = null, this.init("tooltip", g, c)
	};
	d.DEFAULTS = {
		animation : !0,
		placement : "top",
		selector : !1,
		template : '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
		trigger : "hover focus",
		title : "",
		delay : 0,
		html : !1,
		container : !1
	}, d.prototype.init = function(j, q, p) {
		this.enabled = !0, this.type = j, this.$element = e(q), this.options = this.getOptions(p);
		for (var o = this.options.trigger.split(" "), n = o.length; n--;) {
			var m = o[n];
			if ("click" == m) {
				this.$element.on("click." + this.type, this.options.selector, e.proxy(this.toggle, this))
			} else {
				if ("manual" != m) {
					var l = "hover" == m ? "mouseenter" : "focus", k = "hover" == m ? "mouseleave" : "blur";
					this.$element.on(l + "." + this.type, this.options.selector, e.proxy(this.enter, this)), this.$element.on(k + "." + this.type, this.options.selector, e.proxy(this.leave, this))
				}
			}
		}
		this.options.selector ? this._options = e.extend({}, this.options, {
					trigger : "manual",
					selector : ""
				}) : this.fixTitle()
	}, d.prototype.getDefaults = function() {
		return d.DEFAULTS
	}, d.prototype.getOptions = function(c) {
		return c = e.extend({}, this.getDefaults(), this.$element.data(), c), c.delay && "number" == typeof c.delay && (c.delay = {
			show : c.delay,
			hide : c.delay
		}), c
	}, d.prototype.getDelegateOptions = function() {
		var g = {}, h = this.getDefaults();
		return this._options && e.each(this._options, function(b, c) {
					h[b] != c && (g[b] = c)
				}), g
	}, d.prototype.enter = function(g) {
		var h = g instanceof this.constructor ? g : e(g.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
		return clearTimeout(h.timeout), h.hoverState = "in", h.options.delay && h.options.delay.show ? void(h.timeout = setTimeout(function() {
					"in" == h.hoverState && h.show()
				}, h.options.delay.show)) : h.show()
	}, d.prototype.leave = function(g) {
		var h = g instanceof this.constructor ? g : e(g.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
		return clearTimeout(h.timeout), h.hoverState = "out", h.options.delay && h.options.delay.hide ? void(h.timeout = setTimeout(function() {
					"out" == h.hoverState && h.hide()
				}, h.options.delay.hide)) : h.hide()
	}, d.prototype.show = function() {
		var E = e.Event("show.bs." + this.type);
		if (this.hasContent() && this.enabled) {
			if (this.$element.trigger(E), E.isDefaultPrevented()) {
				return
			}
			var D = this.tip();
			this.setContent(), this.options.animation && D.addClass("fade");
			var C = "function" == typeof this.options.placement ? this.options.placement.call(this, D[0], this.$element[0]) : this.options.placement, B = /\s?auto?\s?/i, A = B.test(C);
			A && (C = C.replace(B, "") || "top"), D.detach().css({
						top : 0,
						left : 0,
						display : "block"
					}).addClass(C), this.options.container ? D.appendTo(this.options.container) : D.insertAfter(this.$element);
			var z = this.getPosition(), y = D[0].offsetWidth, x = D[0].offsetHeight;
			if (A) {
				var w = this.$element.parent(), v = C, u = document.documentElement.scrollTop || document.body.scrollTop, t = "body" == this.options.container ? window.innerWidth : w.outerWidth(), s = "body" == this.options.container ? window.innerHeight : w.outerHeight(), r = "body" == this.options.container ? 0 : w.offset().left;
				C = "bottom" == C && z.top + z.height + x - u > s ? "top" : "top" == C && z.top - u - x < 0 ? "bottom" : "right" == C && z.right + y > t ? "left" : "left" == C && z.left - y < r ? "right" : C, D.removeClass(v).addClass(C)
			}
			var q = this.getCalculatedOffset(C, z, y, x);
			this.applyPlacement(q, C), this.$element.trigger("shown.bs." + this.type)
		}
	}, d.prototype.applyPlacement = function(v, u) {
		var t, s = this.tip(), r = s[0].offsetWidth, q = s[0].offsetHeight, p = parseInt(s.css("margin-top"), 10), o = parseInt(s.css("margin-left"), 10);
		isNaN(p) && (p = 0), isNaN(o) && (o = 0), v.top = v.top + p, v.left = v.left + o, s.offset(v).addClass("in");
		var n = s[0].offsetWidth, m = s[0].offsetHeight;
		if ("top" == u && m != q && (t = !0, v.top = v.top + q - m), /bottom|top/.test(u)) {
			var l = 0;
			v.left < 0 && (l = -2 * v.left, v.left = 0, s.offset(v), n = s[0].offsetWidth, m = s[0].offsetHeight), this.replaceArrow(l - r + n, n, "left")
		} else {
			this.replaceArrow(m - q, m, "top")
		}
		t && s.offset(v)
	}, d.prototype.replaceArrow = function(h, g, j) {
		this.arrow().css(j, h ? 50 * (1 - h / g) + "%" : "")
	}, d.prototype.setContent = function() {
		var g = this.tip(), c = this.getTitle();
		g.find(".tooltip-inner")[this.options.html ? "html" : "text"](c), g.removeClass("fade in top bottom left right")
	}, d.prototype.hide = function() {
		function g() {
			"in" != k.hoverState && j.detach()
		}
		var k = this, j = this.tip(), h = e.Event("hide.bs." + this.type);
		return this.$element.trigger(h), h.isDefaultPrevented() ? void 0 : (j.removeClass("in"), e.support.transition && this.$tip.hasClass("fade") ? j.one(e.support.transition.end, g).emulateTransitionEnd(150) : g(), this.$element.trigger("hidden.bs." + this.type), this)
	}, d.prototype.fixTitle = function() {
		var b = this.$element;
		(b.attr("title") || "string" != typeof b.attr("data-original-title")) && b.attr("data-original-title", b.attr("title") || "").attr("title", "")
	}, d.prototype.hasContent = function() {
		return this.getTitle()
	}, d.prototype.getPosition = function() {
		var c = this.$element[0];
		return e.extend({}, "function" == typeof c.getBoundingClientRect ? c.getBoundingClientRect() : {
					width : c.offsetWidth,
					height : c.offsetHeight
				}, this.$element.offset())
	}, d.prototype.getCalculatedOffset = function(h, g, k, j) {
		return "bottom" == h ? {
			top : g.top + g.height,
			left : g.left + g.width / 2 - k / 2
		} : "top" == h ? {
			top : g.top - j,
			left : g.left + g.width / 2 - k / 2
		} : "left" == h ? {
			top : g.top + g.height / 2 - j / 2,
			left : g.left - k
		} : {
			top : g.top + g.height / 2 - j / 2,
			left : g.left + g.width
		}
	}, d.prototype.getTitle = function() {
		var h, g = this.$element, j = this.options;
		return h = g.attr("data-original-title") || ("function" == typeof j.title ? j.title.call(g[0]) : j.title)
	}, d.prototype.tip = function() {
		return this.$tip = this.$tip || e(this.options.template)
	}, d.prototype.arrow = function() {
		return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
	}, d.prototype.validate = function() {
		this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
	}, d.prototype.enable = function() {
		this.enabled = !0
	}, d.prototype.disable = function() {
		this.enabled = !1
	}, d.prototype.toggleEnabled = function() {
		this.enabled = !this.enabled
	}, d.prototype.toggle = function(g) {
		var h = g ? e(g.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type) : this;
		h.tip().hasClass("in") ? h.leave(h) : h.enter(h)
	}, d.prototype.destroy = function() {
		this.hide().$element.off("." + this.type).removeData("bs." + this.type)
	};
	var f = e.fn.tooltip;
	e.fn.tooltip = function(b) {
		return this.each(function() {
					var h = e(this), g = h.data("bs.tooltip"), c = "object" == typeof b && b;
					g || h.data("bs.tooltip", g = new d(this, c)), "string" == typeof b && g[b]()
				})
	}, e.fn.tooltip.Constructor = d, e.fn.tooltip.noConflict = function() {
		return e.fn.tooltip = f, this
	}
}(window.jQuery), +function(e) {
	var d = function(g, c) {
		this.init("popover", g, c)
	};
	if (!e.fn.tooltip) {
		throw new Error("Popover requires tooltip.js")
	}
	d.DEFAULTS = e.extend({}, e.fn.tooltip.Constructor.DEFAULTS, {
				placement : "right",
				trigger : "click",
				content : "",
				template : '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
			}), d.prototype = e.extend({}, e.fn.tooltip.Constructor.prototype), d.prototype.constructor = d, d.prototype.getDefaults = function() {
		return d.DEFAULTS
	}, d.prototype.setContent = function() {
		var h = this.tip(), g = this.getTarget();
		if (g) {
			return g.find(".arrow").length < 1 && h.addClass("no-arrow"), void h.html(g.html())
		}
		var k = this.getTitle(), j = this.getContent();
		h.find(".popover-title")[this.options.html ? "html" : "text"](k), h.find(".popover-content")[this.options.html ? "html" : "text"](j), h.removeClass("fade top bottom left right in"), h.find(".popover-title").html() || h.find(".popover-title").hide()
	}, d.prototype.hasContent = function() {
		return this.getTarget() || this.getTitle() || this.getContent()
	}, d.prototype.getContent = function() {
		var g = this.$element, c = this.options;
		return g.attr("data-content") || ("function" == typeof c.content ? c.content.call(g[0]) : c.content)
	}, d.prototype.getTarget = function() {
		var g = this.$element, j = this.options, h = g.attr("data-target") || ("function" == typeof j.target ? j.target.call(g[0]) : j.target);
		return h ? "$next" == h ? g.next(".popover") : e(h) : !1
	}, d.prototype.arrow = function() {
		return this.$arrow = this.$arrow || this.tip().find(".arrow")
	}, d.prototype.tip = function() {
		return this.$tip || (this.$tip = e(this.options.template)), this.$tip
	};
	var f = e.fn.popover;
	e.fn.popover = function(b) {
		return this.each(function() {
					var h = e(this), g = h.data("bs.popover"), c = "object" == typeof b && b;
					g || h.data("bs.popover", g = new d(this, c)), "string" == typeof b && g[b]()
				})
	}, e.fn.popover.Constructor = d, e.fn.popover.noConflict = function() {
		return e.fn.popover = f, this
	}
}(window.jQuery), +function(e) {
	var d = function(c) {
		this.element = e(c)
	};
	d.prototype.show = function() {
		var h = this.element, n = h.closest("ul:not(.dropdown-menu)"), m = h.attr("data-target");
		if (m || (m = h.attr("href"), m = m && m.replace(/.*(?=#[^\s]*$)/, "")), !h.parent("li").hasClass("active")) {
			var l = n.find(".active:last a")[0], k = e.Event("show.bs.tab", {
						relatedTarget : l
					});
			if (h.trigger(k), !k.isDefaultPrevented()) {
				var j = e(m);
				this.activate(h.parent("li"), n), this.activate(j, j.parent(), function() {
							h.trigger({
										type : "shown.bs.tab",
										relatedTarget : l
									})
						})
			}
		}
	}, d.prototype.activate = function(h, n, m) {
		function l() {
			k.removeClass("active").find("> .dropdown-menu > .active").removeClass("active"), h.addClass("active"), j ? (h[0].offsetWidth, h.addClass("in")) : h.removeClass("fade"), h.parent(".dropdown-menu") && h.closest("li.dropdown").addClass("active"), m && m()
		}
		var k = n.find("> .active"), j = m && e.support.transition && k.hasClass("fade");
		j ? k.one(e.support.transition.end, l).emulateTransitionEnd(150) : l(), k.removeClass("in")
	};
	var f = e.fn.tab;
	e.fn.tab = function(b) {
		return this.each(function() {
					var g = e(this), c = g.data("bs.tab");
					c || g.data("bs.tab", c = new d(this)), "string" == typeof b && c[b]()
				})
	}, e.fn.tab.Constructor = d, e.fn.tab.noConflict = function() {
		return e.fn.tab = f, this
	}, e(document).on("click.bs.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]', function(c) {
				c.preventDefault(), e(this).tab("show")
			})
}(window.jQuery), +function(f, e, h) {
	var g = function(d, j) {
		this.$ = f(d), this.options = this.getOptions(j), this.init()
	};
	g.DEFAULTS = {
		container : "body",
		move : !0
	}, g.prototype.getOptions = function(c) {
		return c = f.extend({}, g.DEFAULTS, this.$.data(), c)
	}, g.prototype.init = function() {
		this.handleMouseEvents()
	}, g.prototype.handleMouseEvents = function() {
		var c = this.$, j = this.options;
		c.mousedown(function(x) {
					function w(n) {
						b = !0;
						var m = n.pageX, l = n.pageY, k = {
							left : m - p.x,
							top : l - p.y
						};
						c.removeClass("drag-ready").addClass("dragging"), j.move && c.css(k), j.hasOwnProperty("drag") && f.isFunction(j.drag) && j.drag({
									event : n,
									element : c,
									startOffset : p,
									pos : k,
									offset : {
										x : m - q.x,
										y : l - q.y
									},
									smallOffset : {
										x : m - d.x,
										y : l - d.y
									}
								}), d.x = m, d.y = l, j.stopPropagation && n.stopPropagation()
					}
					function v(l) {
						if (f(h).unbind("mousemove", w).unbind("mouseup", v), !b) {
							return void c.removeClass("drag-ready")
						}
						var k = {
							left : l.pageX - p.x,
							top : l.pageY - p.y
						};
						c.removeClass("drag-ready").removeClass("dragging"), j.move && c.css(k), j.hasOwnProperty("finish") && f.isFunction(j.finish) && j.finish({
									event : l,
									element : c,
									pos : k,
									offset : {
										x : l.pageX - q.x,
										y : l.pageY - q.y
									},
									smallOffset : {
										x : l.pageX - d.x,
										y : l.pageY - d.y
									}
								}), l.preventDefault(), j.stopPropagation && l.stopPropagation()
					}
					if (j.hasOwnProperty("before") && f.isFunction(j.before)) {
						var u = j.before({
									event : x,
									element : c
								});
						if (void 0 != u && !u) {
							return
						}
					}
					var t = f(j.container), s = c.offset(), r = t.offset(), q = {
						x : x.pageX,
						y : x.pageY
					}, p = {
						x : x.pageX - s.left + r.left,
						y : x.pageY - s.top + r.top
					}, d = f.extend({}, q), b = !1;
					c.addClass("drag-ready"), f(h).bind("mousemove", w).bind("mouseup", v), x.preventDefault(), j.stopPropagation && x.stopPropagation()
				})
	}, f.fn.draggable = function(c) {
		return this.each(function() {
					var j = f(this), d = j.data("zui.draggable"), b = "object" == typeof c && c;
					d || j.data("zui.draggable", d = new g(this, b)), "string" == typeof c && d[c]()
				})
	}, f.fn.draggable.Constructor = g
}(jQuery, window, document, Math), +function(g, f, k, j) {
	var h = function(d, e) {
		this.$ = g(d), this.options = this.getOptions(e), this.init()
	};
	h.DEFAULTS = {
		container : "body",
		flex : !1,
		deviation : 5,
		sensorOffsetX : 0,
		sensorOffsetY : 0,
		nested : !1
	}, h.prototype.getOptions = function(c) {
		return c = g.extend({}, h.DEFAULTS, this.$.data(), c)
	}, h.prototype.callEvent = function(d, e) {
		return g.callEvent(this.options[d], e, this)
	}, h.prototype.init = function() {
		this.handleMouseEvents()
	}, h.prototype.handleMouseEvents = function() {
		var c = this.$, l = this, d = this.options;
		this.$triggerTarget = d.trigger ? (g.isFunction(d.trigger) ? d.trigger(c) : c.find(d.trigger)).first() : c, this.$triggerTarget.on("mousedown", function(F) {
					function E(u) {
						var t = {
							left : u.pageX,
							top : u.pageY
						};
						if (!(j.abs(t.left - J.left) < d.deviation && j.abs(t.top - J.top) < d.deviation)) {
							if (null == y) {
								var q = x.css("position");
								"absolute" != q && "relative" != q && "fixed" != q && (B = q, x.css("position", "relative")), y = c.clone().removeClass("drag-from").addClass("drag-shadow").css({
											position : "absolute",
											width : c.outerWidth(),
											transition : "none"
										}).appendTo(x), c.addClass("dragging"), l.callEvent("start", {
											event : u,
											element : c
										})
							}
							var p = {
								left : t.left - H.left,
								top : t.top - H.top
							}, o = {
								left : p.left - I.left,
								top : p.top - I.top
							};
							y.css(o);
		({
								left : t.left - G.left,
								top : t.top - G.top
							});
							G.left = t.left, G.top = t.top;
							var s = !1;
							w = !1, d.flex || A.removeClass("drop-to");
							var n = null;
							if (A.each(function() {
										var r = g(this), N = r.offset(), M = r.width(), L = r.height(), K = N.left + d.sensorOffsetX, v = N.top + d.sensorOffsetY;
										return t.left > K && t.top > v && t.left < K + M && t.top < v + L && (n && n.removeClass("drop-to"), n = r, !d.nested) ? !1 : void 0
									}), n) {
								w = !0;
								var m = n.data("id");
								c.data("id") != m && (e = !1), (null == z || z.data("id") != m && !e) && (s = !0), z = n, d.flex && A.removeClass("drop-to"), z.addClass("drop-to")
							}
							d.flex ? null != z && z.length && (w = !0) : (c.toggleClass("drop-in", w), y.toggleClass("drop-in", w)), l.callEvent("drag", {
										event : u,
										isIn : w,
										target : z,
										element : c,
										isNew : s,
										selfTarget : e,
										clickOffset : H,
										offset : p,
										position : {
											left : p.left - I.left,
											top : p.top - I.top
										},
										mouseOffset : t
									})
						}
					}
					function D(t) {
						if (B && x.css("position", B), null == y) {
							return c.removeClass("drag-from"), void g(k).unbind("mousemove", E).unbind("mouseup", D)
						}
						w || (z = null);
						var q = !0, p = {
							left : t.pageX,
							top : t.pageY
						}, m = {
							left : p.left - H.left,
							top : p.top - H.top
						}, o = {
							left : p.left - G.left,
							top : p.top - G.top
						};
						G.left = p.left, G.top = p.top;
						var n = {
							event : t,
							isIn : w,
							target : z,
							element : c,
							isNew : !e && null != z,
							selfTarget : e,
							offset : m,
							mouseOffset : p,
							position : {
								left : m.left - I.left,
								top : m.top - I.top
							},
							lastMouseOffset : G,
							moveOffset : o
						};
						q = l.callEvent("beforeDrop", n), q && w && l.callEvent("drop", n), g(k).unbind("mousemove", E).unbind("mouseup", D), A.removeClass("drop-to"), c.removeClass("dragging").removeClass("drag-from"), y.remove(), l.callEvent("finish", n), t.preventDefault()
					}
					if (d.hasOwnProperty("before") && g.isFunction(d.before)) {
						var C = d.before({
									event : F,
									element : c
								});
						if (void 0 != C && !C) {
							return
						}
					}
					var B, A = g.isFunction(d.target) ? d.target(c) : g(d.target), z = null, y = null, x = g(d.container).first(), w = !1, e = !0, b = c.offset(), J = {
						left : F.pageX,
						top : F.pageY
					}, I = x.offset(), H = ({
						left : b.left - I.left,
						top : b.top - I.top
					}, {
						left : J.left - b.left,
						top : J.top - b.top
					}), G = {
						left : J.left,
						top : J.top
					};
					c.addClass("drag-from"), g(k).bind("mousemove", E).bind("mouseup", D), F.preventDefault()
				})
	}, h.prototype.reset = function() {
		this.$triggerTarget.off("mousedown"), this.handleMouseEvents()
	}, g.fn.droppable = function(c) {
		return this.each(function() {
					var l = g(this), e = l.data("zui.droppable"), b = "object" == typeof c && c;
					e || l.data("zui.droppable", e = new h(this, b)), "string" == typeof c && e[c]()
				})
	}, g.fn.droppable.Constructor = h
}(jQuery, window, document, Math), +function(h, g, m, l) {
	var k = "zui.datatable", j = function(d, e) {
		this.name = k, this.$ = h(d), this.isTable = "TABLE" === this.$[0].tagName, this.isTable ? this.id = "datatable-" + (this.$.attr("id") || h.uuid()) : (this.$datatable = this.$.addClass("datatable"), this.$.attr("id") ? this.id = this.$.attr("id") : (this.id = "datatable-" + h.uuid(), this.$.attr("id", this.id))), this.getOptions(e), this.load(), this.render()
	};
	j.DEFAULTS = {
		checkable : !1,
		checkByClickRow : !0,
		checkedClass : "active",
		sortable : !1,
		fixedHeader : !0,
		fixedHeaderOffset : 0,
		fixedLeftWidth : "30%",
		fixedRightWidth : "30%",
		flexHeadDrag : !0,
		rowHover : !0,
		colHover : !0,
		customizable : !1,
		minColWidth : 20,
		minFixedLeftWidth : 200,
		minFixedRightWidth : 200,
		minFlexAreaWidth : 200
	}, j.prototype.getOptions = function(d) {
		var e = this.$, d = h.extend({}, j.DEFAULTS, this.$.data(), d);
		d.tableClass || (d.tableClass = "", e.hasClass("table-bordered") && (d.tableClass += " table-bordered"), (e.hasClass("table-hover") || d.rowHover) && (d.tableClass += " table-hover"), e.hasClass("table-striped") && (d.tableClass += " table-striped")), this.options = d
	}, j.prototype.load = function() {
		var w = this.options.data, v = (this.options, this.$);
		if (!w) {
			if (!this.isTable) {
				throw new Error("No data avaliable!")
			}
			w = {
				cols : [],
				rows : []
			};
			var u, t, s, r, q = w.cols, p = w.rows;
			v.find("thead > tr:first").children("th").each(function() {
						u = h(this), q.push(h.extend({
									text : u.html(),
									flex : !1 || u.hasClass("flex-col"),
									width : "auto",
									cssClass : u.attr("class"),
									css : u.attr("style"),
									type : "string",
									sort : !u.hasClass("sort-disabled")
								}, u.data()))
					}), v.find("tbody > tr").each(function() {
						t = h(this), r = h.extend({
									data : [],
									checked : !1,
									cssClass : t.attr("class"),
									css : t.attr("style"),
									id : t.attr("id")
								}, t.data()), t.children("td").each(function() {
									s = h(this), r.data.push(h.extend({
												cssClass : s.attr("class"),
												css : s.attr("style"),
												text : s.html()
											}, s.data()))
								}), p.push(r)
					})
		}
		w.flexStart = -1, w.flexEnd = -1;
		var q = w.cols;
		w.colsLength = q.length;
		for (var o = 0; o < q.length; ++o) {
			var n = q[o];
			n.flex && (w.flexStart < 0 && (w.flexStart = o), w.flexEnd = o)
		}
		0 === w.flexStart && w.flexEnd === q.length && (w.flexStart = -1, w.flexEnd = -1), w.flexArea = w.flexStart >= 0, w.fixedRight = w.flexEnd >= 0 && w.flexEnd < q.length - 1, w.fixedLeft = w.flexStart > 0, w.flexStart < 0 && w.flexEnd < 0 && (w.fixedLeft = !0, w.flexStart = q.length, w.flexEnd = q.length), this.data = w, this.callEvent("afterLoad", {
					data : w
				})
	}, j.prototype.html = function() {
		var Q = !h("#" + this.id).empty().length, P = "", O = this.options, N = this.data, M = this.data.cols, L = "", K = "", J = "", I = this.data.rows, H = '<div class="datatable-rows-span datatable-span {0}"><div class="datatable-wrapper"><table class="table' + O.tableClass + '"><tbody>{1}</tbody></table>{2}</div></div>', G = '<div class="datatable-head-span datatable-span {0}"><div class="datatable-wrapper"><table class="table' + O.tableClass + '"><thead><tr>{1}</tr></thead></table>{2}</div></div>';
		Q ? P += '<div class="datatable' + (O.sortable ? " sortable" : "") + " " + (O.customizable ? " customizable" : "") + '" id="' + this.id + '">' : this.$datatable.toggleClass("sortable", O.sortable).toggleClass("customizable", O.customizable), P += '<div class="datatable-head">';
		for (var F, E, D, C = 0; C < M.length; C++) {
			E = M[C], D = "", "undefined" == typeof E.customizable && (E.customizable = !0), "undefined" == typeof E.sort ? E.sort = !0 : "down" === E.sort ? D = "sort-down" : "up" === E.sort ? D = "sort-up" : E.sort === !1 && (D = "sort-disabled"), F = '<th class="' + (E.cssClass || "") + " " + (E.colClass || "") + " " + D + '" data-index="' + C + '" data-type="' + E.type + '" style="' + E.css + '">' + E.text + (E.customizable && C != N.flexStart - 1 && C != N.flexEnd && C < M.length - 1 ? '<div class="size-handle"></div>' : "") + "</th>", 0 == C && O.checkable && (F = '<th data-index="check" class="check-all check-btn"><i class="icon-check-empty"></i></th>' + F), C < N.flexStart ? L += F : C >= N.flexStart && C <= N.flexEnd ? J += F : C > N.flexEnd && (K += F)
		}
		N.fixedLeft && (P += G.format("fixed-left", L, '<div class="size-handle size-handle-head size-handle-left"></div>')), N.flexArea && (P += G.format("flexarea", J, '<div class="scrolled-shadow scrolled-in-shadow"></div><div class="scrolled-shadow scrolled-out-shadow"></div>')), N.fixedRight && (P += G.format("fixed-right", K, '<div class="size-handle size-handle-head size-handle-right"></div>')), P += "</div>", P += '<div class="datatable-rows">';
		var B, A, C, z, y, x;
		L = "", K = "", J = "";
		for (var w = 0; w < I.length; ++w) {
			for (A = I[w], y = A.cssClass || "", A.checked && (y += " " + (O.checkedClass || "")), "undefined" == typeof A.id && (A.id = w), A.index = w, B = '<tr class="' + y + '" data-index="' + w + '" data-id="' + A.id + '">', L += B, K += B, J += B, C = 0; C < A.data.length; ++C) {
				x = A.data[C], h.isPlainObject(x) || (x = {
					text : x,
					row : w,
					index : C
				}, A.data[C] = x), z = '<td data-row="' + w + '" data-index="' + C + '" data-flex="false" data-type="' + M[C].type + '" class="' + (x.cssClass || "") + " " + (M[C].colClass || "") + '" style="' + (x.css || "") + '">' + x.text + "</td>", 0 == C && O.checkable && (z = '<td data-index="check" class="check-row check-btn"><i class="icon-check-empty"></i></td>' + z), C < N.flexStart ? L += z : C >= N.flexStart && C <= N.flexEnd ? J += z : C > N.flexEnd && (K += z)
			}
			L += "</tr>", K += "</tr>", J += "</tr>"
		}
		return N.fixedLeft && (P += H.format("fixed-left", L, "")), N.flexArea && (P += H.format("flexarea", J, '<div class="scrolled-shadow scrolled-in-shadow"></div><div class="scrolled-shadow scrolled-out-shadow"></div><div class="scroll-slide"><div class="bar"></div></div>')), N.fixedRight && (P += H.format("fixed-right", K, "")), P += "</div>", Q && (P += "</div>"), P
	}, j.prototype.render = function() {
		this.isTable ? (this.$.attr("data-datatable-id", this.id).hide(), this.$.after(this.html()), this.$datatable = h("#" + this.id)) : this.$.html(this.html()), this.$dataSpans = this.$datatable.children(".datatable-head, .datatable-rows").find(".datatable-span"), this.$rowsSpans = this.$datatable.children(".datatable-rows").children(".datatable-rows-span"), this.$headSpans = this.$datatable.children(".datatable-head").children(".datatable-head-span"), this.$cells = this.$dataSpans.find("td, th"), this.$dataCells = this.$cells.filter("td"), this.$headCells = this.$cells.filter("th"), this.$rows = this.$rowsSpans.find(".table > tbody > tr");
		var aD = this.options, aC = this, aB = this.data, aA = this.$cells, az = this.$dataCells, ay = (this.$headCells, this.$datatable);
		if (aD.rowHover) {
			var ax = "hover";
			this.$rowsSpans.on("mouseenter", "td", function() {
						az.filter("." + ax).removeClass(ax), aC.$rows.filter("." + ax).removeClass(ax), aC.$rows.filter('[data-index="' + h(this).addClass(ax).closest("tr").data("index") + '"]').addClass(ax)
					}).on("mouseleave", "td", function() {
						az.filter("." + ax).removeClass(ax), aC.$rows.filter("." + ax).removeClass(ax)
					})
		}
		if (aD.colHover) {
			var aw = "col-hover";
			this.$headSpans.on("mouseenter", "th", function() {
						aA.filter("." + aw).removeClass(aw), aA.filter('[data-index="' + h(this).data("index") + '"]').addClass(aw)
					}).on("mouseleave", "th", function() {
						aA.filter("." + aw).removeClass(aw)
					})
		}
		if (this.data.flexArea) {
			var av, au, at, ar, aq, ap, ao, an = ay.find(".scroll-slide"), am = ay.find(".datatable-span.flexarea .table"), al = ay.find(".datatable-rows-span.flexarea .table"), ak = an.children(".bar"), aj = aC.id + "_scrollOffset";
			this.width = ay.width(), ay.resize(function() {
						aC.width = ay.width()
					});
			var ai = function(e, c) {
				aq = l.max(0, l.min(av - au, e)), c || ay.addClass("scrolling"), ak.css("left", aq), ao = 0 - l.floor((at - av) * aq / (av - au)), am.css("left", ao), ar = aq, ay.toggleClass("scrolled-in", aq > 2).toggleClass("scrolled-out", av - au - 2 > aq), store.pageSet(aj, aq)
			}, ah = function() {
				av = an.width(), at = al.width(), au = l.floor(av * av / at), ak.css("width", au), al.css("min-width", av), ay.toggleClass("show-scroll-slide", at > av), ap || av === au || (ap = !0, ai(store.pageGet(aj, 0), !0)), ay.hasClass("size-changing") && ai(aq, !0)
			};
			an.resize(ah), al.resize(ah), ah();
			var ag = {
				move : !1,
				stopPropagation : !0,
				drag : function(c) {
					ai(ak.position().left + c.smallOffset.x * (c.element.hasClass("bar") ? 1 : -1))
				},
				finish : function() {
					ay.removeClass("scrolling")
				}
			};
			ak.draggable(ag), aD.flexHeadDrag && ay.find(".datatable-head-span.flexarea").draggable(ag), an.mousedown(function(e) {
						var c = e.pageX - an.offset().left;
						ai(c - au / 2)
					})
		}
		if (aD.checkable) {
			var af, ae = aC.id + "_" + ab, ad = aD.checkedClass, ac = function() {
				var e = aC.$rowsSpans.first().find(".table > tbody > tr"), n = e.filter("." + ad), f = {
					checkedAll : e.length === n.length && n.length > 0,
					checks : n.map(function() {
								return af = h(this).data("id")
							}).toArray()
				};
				h.each(aB.rows, function(o, p) {
							p.checked = h.inArray(p.id, f.checks) > -1
						}), aC.$headSpans.find(".check-all").toggleClass("checked", f.checkedAll), store.pageSet(ae, f), aC.callEvent("checksChanged", {
							checks : f
						})
			};
			this.$rowsSpans.on("click", aD.checkByClickRow ? "tr" : ".check-row", function() {
						aC.$rows.filter('[data-index="' + h(this).closest("tr").data("index") + '"]').toggleClass(ad), ac()
					}), this.$headSpans.find(".check-all").click(function() {
						aC.$rows.toggleClass(ad, h(this).toggleClass("checked").hasClass("checked")), ac()
					});
			var ab = store.pageGet(ae);
			ab && (this.$headSpans.find(".check-all").toggleClass("checked", ab.checkedAll), ab.checkedAll ? aC.$rows.addClass(ad) : (aC.$rows.removeClass(ad), h.each(ab.checks, function(e, c) {
						aC.$rows.filter('[data-id="' + c + '"]').addClass(ad)
					})))
		}
		if (aD.fixedHeader) {
			var aa, Z, Y, X = ay.children(".datatable-head"), W = aD.fixedHeaderOffset || h(".navbar.navbar-fixed-top").height() || 0, V = function() {
				aa = ay.offset().top, Y = h(g).scrollTop(), Z = ay.height(), ay.toggleClass("head-fixed", Y + W > aa && aa + Z > Y + W), ay.hasClass("head-fixed") ? X.css({
							width : ay.width(),
							top : W
						}) : X.attr("style", "")
			};
			h(g).scroll(V), V()
		}
		if (aD.sortable) {
			var U;
			this.$headSpans.on("click", "th:not(.sort-disabled, .check-btn)", function() {
						ay.hasClass("size-changing") || aC.sortTable(h(this))
					})
		}
		if (aD.customizable) {
			var T, U, S, R, d = this.$headSpans.find(".size-handle"), b = function(e, c) {
				var f = {};
				e.hasClass("size-handle-head") ? (T = e.closest(".datatable-head-span").width(), e.hasClass("size-handle-left") ? (f.change = "fixedLeftWidth", f.oldWidth = aC.fixedLeftWidth, aC.fixedLeftWidth = l.min(aC.width - (aC.fixedRightWidth || aC.$headSpans.filter(".fixed-right").width()) - aD.minFlexAreaWidth, l.max(aD.minFixedLeftWidth, T + c)), f.newWidth = aC.fixedLeftWidth) : (f.change = "fixedRightWidth", f.oldWidth = aC.fixedRightWidth, aC.fixedRightWidth = l.min(aC.width - (aC.fixedLeftWidth || aC.$headSpans.filter(".fixed-left").width()) - aD.minFlexAreaWidth, l.max(aD.minFixedRightWidth, T - c)), f.newWidth = aC.fixedRightWidth)) : (U = e.closest("th"), S = aB.cols[U.data("index")], T = S.width, "auto" === T ? (e = U.closest(".datatable-head-span"), T = e.width(), e
						.hasClass("fixed-left") ? (f.change = "fixedLeftWidth", f.oldWidth = aC.fixedLeftWidth, aC.fixedLeftWidth = l.min(aC.width - (aC.fixedRightWidth || aC.$headSpans.filter(".fixed-right").width()) - aD.minFlexAreaWidth, l.max(aD.minFixedLeftWidth, T + c)), f.newWidth = aC.fixedLeftWidth) : e.hasClass("fixed-right") && (f.change = "fixedRightWidth", f.oldWidth = aC.fixedRightWidth, aC.fixedRightWidth = l.min(aC.width - (aC.fixedLeftWidth || aC.$headSpans.filter(".fixed-left").width()) - aD.minFlexAreaWidth, l.max(aD.minFixedRightWidth, T - c)), f.newWidth = aC.fixedRightWidth)) : (f.change = "colWidth", f.oldWidth = S.width, f.colIndex = S.index, S.width = l.max(aD.minColWidth, T + c), f.newWidth = S.width)), aC.refreshSize(), aC.callEvent("sizeChanged", {
							changes : f
						})
			};
			d.draggable({
						move : !1,
						stopPropagation : !0,
						before : function() {
							R = null, ay.addClass("size-changing")
						},
						drag : function(c) {
							b(c.element, c.smallOffset.x)
						},
						finish : function() {
							ay.removeClass("size-changing")
						}
					})
		}
		this.refresh(), this.callEvent("ready")
	}, j.prototype.sortTable = function(M) {
		var L = self.id + "_datatableSorter", K = store.pageGet(L);
		if (M || (M = K ? this.$headCells.filter('[data-index="' + K.index + '"]').addClass("sort-" + K.type) : this.$headCells.filter(".sort-up, .sort-down").first()), M.length) {
			var J, I, H, G = this.data, F = G.cols, E = G.rows, D = this.$headCells;
			J = !M.hasClass("sort-up"), D.removeClass("sort-up sort-down"), M.addClass(J ? "sort-up" : "sort-down"), H = M.data("index"), J = M.hasClass("sort-up"), h.each(F, function(d, c) {
						d == H || "up" !== c.sort && "down" !== c.sort ? d == H && (c.sort = J ? "up" : "down", I = c.type) : c.sort = !0
					});
			var C, B, A, z = this.$dataCells.filter('[data-index="' + H + '"]');
			E.sort(function(d, c) {
						return d = d.data[H], c = c.data[H], C = z.filter('[data-row="' + d.row + '"]').text(), B = z.filter('[data-row="' + c.row + '"]').text(), "number" === I ? (C = parseFloat(C), B = parseFloat(B)) : "date" === I ? (C = Date.parse(C), B = Date.parse(B)) : (C = C.toLowerCase(), B = B.toLowerCase()), A = C > B ? 1 : B > C ? -1 : 0, J && (A = -1 * A), A
					});
			var y, x, w, v = this.$rows, u = [];
			h.each(E, function(d, e) {
						y = v.filter('[data-index="' + e.index + '"]'), y.each(function(c) {
									w = h(this), x = u[c], x ? x.after(w) : w.parent().prepend(w), u[c] = w
								})
					});
			var K = {
				index : H,
				type : J ? "up" : "down"
			};
			store.pageSet(L, K), this.callEvent("sort", {
						sorter : K
					})
		}
	}, j.prototype.callEvent = function(e, d) {
		var f = this.$.callEvent(e + "." + this.name, d, this).result;
		return !(void 0 != f && !f)
	}, j.prototype.refreshSize = function() {
		var v = this.$datatable, u = this.options, t = this.data.rows, s = this.data.cols;
		v.find(".datatable-span.fixed-left").css("width", this.fixedLeftWidth || u.fixedLeftWidth), v.find(".datatable-span.fixed-right").css("width", this.fixedRightWidth || u.fixedRightWidth);
		for (var r = function(e) {
			var f = 0;
			return e.css("height", "auto"), e.each(function() {
						console.log(h(this)), f = l.max(f, h(this).height())
					}), f
		}, q = this.$dataCells, p = this.$cells, o = this.$headCells, n = 0; n < s.length; ++n) {
			p.filter('[data-index="' + n + '"]').css("width", s[n].width)
		}
		o.height(r(o));
		for (var d, n = 0; n < t.length; ++n) {
			d = q.filter('[data-row="' + n + '"]'), d.height(r(d))
		}
	}, j.prototype.refresh = function() {
		this.refreshSize(), this.sortTable()
	}, h.fn.datatable = function(c) {
		return this.each(function() {
					var f = h(this), e = f.data(k), b = "object" == typeof c && c;
					e || f.data(k, e = new j(this, b)), "string" == typeof c && e[c]()
				})
	}, h.fn.datatable.Constructor = j
}(jQuery, window, document, Math);
function createLink(e, c, f, b, d) {
	if (!b) {
		b = config.defaultView
	}
	if (!d) {
		d = false
	}
	if (f) {
		f = f.split("&");
		for (i = 0; i < f.length; i++) {
			f[i] = f[i].split("=")
		}
	}
	if (config.requestType == "PATH_INFO") {
		link = config.webRoot + e + config.requestFix + c;
		if (f) {
			if (config.pathType == "full") {
				for (i = 0; i < f.length; i++) {
					link += config.requestFix + f[i][0] + config.requestFix + f[i][1]
				}
			} else {
				for (i = 0; i < f.length; i++) {
					link += config.requestFix + f[i][1]
				}
			}
		}
		link += "." + b
	} else {
		link = config.router + "?" + config.moduleVar + "=" + e + "&" + config.methodVar + "=" + c + "&" + config.viewVar + "=" + b;
		if (f) {
			for (i = 0; i < f.length; i++) {
				link += "&" + f[i][0] + "=" + f[i][1]
			}
		}
	}
	if (g == "yes" || d) {
		var g = config.requestType == "PATH_INFO" ? "?onlybody=yes" : "&onlybody=yes";
		link = link + g
	}
	return link
}
function setSearchBox() {
	$("#typeSelector a").click(function() {
				$("#typeSelector li.active").removeClass("active");
				var b = $(this);
				b.closest("li").addClass("active");
				$("#searchType").val(b.data("value"));
				$("#searchTypeName").text(b.text())
			})
}
function shortcut() {
	objectType = $("#searchType").attr("value");
	objectValue = $("#searchQuery").attr("value");
	if (objectType && objectValue) {
		location.href = createLink(objectType, "view", "id=" + objectValue)
	}
}
function showDropMenu(d, g, e, f, c) {
	var b = $("#currentItem").closest("li");
	if (b.hasClass("show")) {
		b.removeClass("show");
		return
	}
	if (!b.data("showagain")) {
		b.data("showagain", true);
		$(document).click(function() {
					b.removeClass("show")
				});
		$("#dropMenu, #currentItem").click(function(h) {
					h.stopPropagation()
				})
	}
	$.get(createLink(d, "ajaxGetDropMenu", "objectID=" + g + "&module=" + e + "&method=" + f + "&extra=" + c), function(h) {
				$("#dropMenu").html(h).find("#search").focus()
			});
	b.addClass("show")
}
function showDropResult(c, f, d, e, b) {
	$.get(createLink(c, "ajaxGetDropMenu", "objectID=" + f + "&module=" + d + "&method=" + e + "&extra=" + b), function(g) {
				$("#dropMenu").html(g).find("#search").focus()
			})
}
function searchItems(e, c, g, d, f, b) {
	if (e == "") {
		showMenu = 0;
		showDropResult(c, g, d, f, b)
	} else {
		e = encodeURI(e);
		if (e != "-") {
			$.get(createLink(c, "ajaxGetMatchedItems", "keywords=" + e + "&module=" + d + "&method=" + f + "&extra=" + b), function(h) {
						$("#searchResult").html(h)
					})
		}
	}
}
function switchMore() {
	$("#search").width($("#search").width()).focus();
	$("#moreMenu").width($("#defaultMenu").outerWidth());
	$("#searchResult").toggleClass("show-more")
}
function switchDocLib(d, c, e, b) {
	if (c == "doc") {
		if (e != "view" && e != "edit") {
			link = createLink(c, e, "rootID=" + d)
		} else {
			link = createLink("doc", "browse")
		}
	} else {
		if (c == "tree") {
			link = createLink(c, e, "rootID=" + d + "&type=" + b)
		}
	}
	location.href = link
}
function setPing() {
	$("#hiddenwin").attr("src", createLink("misc", "ping"))
}
function setRequiredFields() {
	if (!config.requiredFields) {
		return false
	}
	requiredFields = config.requiredFields.split(",");
	for (i = 0; i < requiredFields.length; i++) {
		$("#" + requiredFields[i]).closest("td,th").prepend("<div class='required required-wrapper'></div>");
		var b = $("#" + requiredFields[i]).closest('[class*="col-"]');
		if (b.parent().hasClass("form-group")) {
			b.addClass("required")
		}
	}
}
function setHelpLink() {
	if (!$.cookie("help")) {
		$.cookie("help", "off", {
					expires : config.cookieLife,
					path : config.webRoot
				})
	}
	className = $.cookie("help") == "off" ? "hidden" : "";
	$("form input[id], form select[id], form textarea[id]").each(function() {
				if ($(this).attr("type") == "hidden" || $(this).attr("type") == "file") {
					return
				}
				currentFieldName = $(this).attr("name") ? $(this).attr("name") : $(this).attr("id");
				if (currentFieldName == "submit" || currentFieldName == "reset") {
					return
				}
				if (currentFieldName.indexOf("[") > 0) {
					currentFieldName = currentFieldName.substr(0, currentFieldName.indexOf("["))
				}
				currentFieldName = currentFieldName.toLowerCase();
				helpLink = createLink("help", "field", "module=" + config.currentModule + "&method=" + config.currentMethod + "&field=" + currentFieldName);
				$(this).after(' <a class="helplink ' + className + '" href=' + helpLink + ' target="_blank">?</a> ')
			});
	$("a.helplink").modalTrigger({
				width : 600,
				type : "iframe"
			})
}
function setPlaceholder() {
	if (typeof(holders) != "undefined") {
		for (var b in holders) {
			if ($("#" + b).prop("tagName") == "INPUT") {
				$("#" + b).attr("placeholder", holders[b])
			}
		}
	}
}
function toggleHelpLink() {
	$(".helplink").toggle();
	if ($.cookie("help") == "off") {
		return $.cookie("help", "on", {
					expires : config.cookieLife,
					path : config.webRoot
				})
	}
	if ($.cookie("help") == "on") {
		return $.cookie("help", "off", {
					expires : config.cookieLife,
					path : config.webRoot
				})
	}
}
function hideTreeBox(b) {
	$.cookie(b, "hide", {
				expires : config.cookieLife,
				path : config.webRoot
			});
	$(".outer").addClass("hide-side");
	$(".side-handle .icon-caret-left").removeClass("icon-caret-left").addClass("icon-caret-right")
}
function showTreeBox(b) {
	$.cookie(b, "show", {
				expires : config.cookieLife,
				path : config.webRoot
			});
	$(".outer").removeClass("hide-side");
	$(".side-handle .icon-caret-right").removeClass("icon-caret-right").addClass("icon-caret-left")
}
function toggleTreeBox() {
	var b = $(".side-handle").data("id");
	if (typeof b == "undefined" || b == null) {
		return
	}
	if ($.cookie(b) == "hide") {
		hideTreeBox(b)
	}
	$(".side-handle").toggle(function() {
				if ($.cookie(b) == "hide") {
					return showTreeBox(b)
				}
				hideTreeBox(b)
			}, function() {
				if ($.cookie(b) == "show") {
					return hideTreeBox(b)
				}
				showTreeBox(b)
			});
	setTimeout(function() {
				$(".outer.with-side").addClass("with-transition")
			}, 1000)
}
function setTreeBox() {
	if ($(".outer > .side").length) {
		$(".outer").addClass("with-side")
	}
	toggleTreeBox()
}
function selectLang(b) {
	$.cookie("lang", b, {
				expires : config.cookieLife,
				path : config.webRoot
			});
	location.href = removeAnchor(location.href)
}
function selectTheme(b) {
	$.cookie("theme", b, {
				expires : config.cookieLife,
				path : config.webRoot
			});
	location.href = removeAnchor(location.href)
}
function removeAnchor(b) {
	pos = b.indexOf("#");
	if (pos > 0) {
		return b.substring(0, pos)
	}
	return b
}
function saveWindowSize() {
	width = $(window).width();
	height = $(window).height();
	$.cookie("windowWidth", width);
	$.cookie("windowHeight", height)
}
function setOuterBox() {
	if ($(".sub-featurebar").length) {
		$("#featurebar").addClass("with-sub")
	}
	var c = $("#wrap .outer > .side");
	var b = function() {
		var e = c.length ? (c.outerHeight() + $("#featurebar").outerHeight() + 20) : 0;
		var d = Math.max(e, $(window).height() - $("#header").height() - $("#footer").height() - 33);
		if (navigator.userAgent.indexOf("MSIE 8.0") >= 0) {
			d -= 40
		}
		$("#wrap .outer").css("min-height", d)
	};
	c.resize(b);
	$(window).resize(b);
	b()
}
function setForm() {
	var b = false;
	$("form").submit(function() {
				submitObj = $(this).find(":submit");
				if ($(submitObj).size() >= 1) {
					var d = submitObj.prop("tagName") == "BUTTON";
					submitLabel = d ? $(submitObj).text() : $(submitObj).attr("value");
					$(submitObj).attr("disabled", "disabled");
					var c = submitObj.attr("data-submitting") || lang.submitting;
					if (d) {
						submitObj.text(c)
					} else {
						$(submitObj).attr("value", c)
					}
					b = true
				}
			});
	$("body").click(function() {
				if (b) {
					$(submitObj).removeAttr("disabled");
					if (submitObj.prop("tagName") == "BUTTON") {
						submitObj.text(submitLabel)
					} else {
						$(submitObj).attr("value", submitLabel)
					}
					$(submitObj).removeClass("button-d")
				}
				b = false
			})
}
function setFormAction(b, c) {
	if (c) {
		$("form").attr("target", c)
	} else {
		$("form").removeAttr("target")
	}
	$("form").attr("action", b).submit()
}
function setImageSize(c, b) {
	if (!b) {
		bodyWidth = $("body").width();
		b = bodyWidth - 470
	}
	if ($(c).width() > b) {
		$(c).attr("width", b)
	}
	$(c).wrap('<a href="' + $(c).attr("src") + '" target="_blank"></a>')
}
function setModalTriggerLink() {
	$(".repolink").modalTrigger({
				width : 960,
				type : "iframe"
			});
	$(".export").modalTrigger({
				width : 650,
				type : "iframe"
			})
}
function setMailto(b, c) {
	link = createLink("user", "ajaxGetContactUsers", "listID=" + c);
	$.get(link, function(d) {
				$("#" + b).replaceWith(d);
				$("#" + b + "_chosen").remove();
				$("#" + b).chosen(defaultChosenOptions)
			})
}
function setComment() {
	$("#commentBox").toggle();
	$(".ke-container").css("width", "100%");
	setTimeout(function() {
				$("#commentBox textarea").focus()
			}, 50)
}
function autoCheck() {
	$(".tablesorter tr :checkbox").click(function() {
				clickInCheckbox = 1
			});
	$(".tablesorter tr").click(function() {
				if (document.activeElement.type != "select-one" && document.activeElement.type != "text") {
					if (typeof(clickInCheckbox) != "undefined" && clickInCheckbox == 1) {
						clickInCheckbox = 0
					} else {
						if ($(this).find(":checkbox").attr("checked")) {
							$(this).find(":checkbox").attr("checked", false)
						} else {
							$(this).find(":checkbox").attr("checked", true)
						}
					}
				}
			})
}
function toggleSearch() {
	$("#bysearchTab").toggle(function() {
				if (browseType == "bymodule") {
					$("#bymoduleTab").removeClass("active")
				} else {
					$("#" + browseType + "Tab").removeClass("active")
				}
				$("#bysearchTab").addClass("active");
				ajaxGetSearchForm();
				$("#querybox").addClass("show")
			}, function() {
				if (browseType == "bymodule") {
					$("#bymoduleTab").addClass("active")
				} else {
					$("#" + browseType + "Tab").addClass("active")
				}
				$("#bysearchTab").removeClass("active");
				$("#querybox").removeClass("show")
			})
}
function ajaxGetSearchForm() {
	if ($("#querybox").html() == "") {
		$.get(createLink("search", "buildForm"), function(b) {
					$("#querybox").html(b)
				})
	}
}
function addItem(e, c) {
	ItemList = document.getElementById(e);
	Target = document.getElementById(c);
	for (var b = 0; b < ItemList.length; b++) {
		var d = ItemList.options[b];
		if (d.selected) {
			flag = true;
			for (var g = 0; g < Target.length; g++) {
				var f = Target.options[g];
				if (f.value == d.value) {
					flag = false
				}
			}
			if (flag) {
				Target.options[Target.options.length] = new Option(d.text, d.value, 0, 0)
			}
		}
	}
}
function delItem(d) {
	ItemList = document.getElementById(d);
	for (var b = ItemList.length - 1; b >= 0; b--) {
		var c = ItemList.options[b];
		if (c.selected) {
			ItemList.options[b] = null
		}
	}
}
function upItem(d) {
	ItemList = document.getElementById(d);
	for (var b = 1; b < ItemList.length; b++) {
		var c = ItemList.options[b];
		if (c.selected) {
			tmpUpValue = ItemList.options[b - 1].value;
			tmpUpText = ItemList.options[b - 1].text;
			ItemList.options[b - 1].value = c.value;
			ItemList.options[b - 1].text = c.text;
			ItemList.options[b].value = tmpUpValue;
			ItemList.options[b].text = tmpUpText;
			ItemList.options[b - 1].selected = true;
			ItemList.options[b].selected = false;
			break
		}
	}
}
function downItem(d) {
	ItemList = document.getElementById(d);
	for (var b = 0; b < ItemList.length; b++) {
		var c = ItemList.options[b];
		if (c.selected) {
			tmpUpValue = ItemList.options[b + 1].value;
			tmpUpText = ItemList.options[b + 1].text;
			ItemList.options[b + 1].value = c.value;
			ItemList.options[b + 1].text = c.text;
			ItemList.options[b].value = tmpUpValue;
			ItemList.options[b].text = tmpUpText;
			ItemList.options[b + 1].selected = true;
			ItemList.options[b].selected = false;
			break
		}
	}
}
function selectItem(d) {
	ItemList = document.getElementById(d);
	for (var b = ItemList.length - 1; b >= 0; b--) {
		var c = ItemList.options[b];
		c.selected = true
	}
}
function ajaxDelete(b, d, c) {
	if (confirm(c)) {
		$.ajax({
					type : "GET",
					url : b,
					dataType : "json",
					success : function(e) {
						if (e.result == "success") {
							$("#" + d).wrap("<div id='tmpDiv'></div>");
							$("#tmpDiv").load(document.location.href + " #" + d, function() {
										$("#tmpDiv").replaceWith($("#tmpDiv").html());
										if (typeof sortTable == "function") {
											sortTable()
										}
										$("#" + d).find("[data-toggle=modal], a.iframe").modalTrigger()
									})
						}
					}
				})
	}
}
function isNum(c) {
	if (c != null) {
		var d, b;
		b = /\d*/i;
		d = c.match(b);
		return (d == c) ? true : false
	}
	return false
}
function setModal() {
	jQuery.fn.modalTrigger = function(f) {
		return $(this).each(function() {
					var g = $(this);
					g.off("click.modalTrigger.zui");
					g.on("click.modalTrigger.zui", function(n) {
								var k = $(this);
								if (k.closest(".body-modal").length) {
									return
								}
								if (k.hasClass("disabled")) {
									return false
								}
								var j = (f ? f.url : false) || k.attr("href") || k.data("url");
								var m = (f ? f.type : false) || k.hasClass("iframe") ? "iframe" : (k.data("type") || "ajax");
								if (m == "iframe") {
									var h = {
										url : j,
										width : k.data("width") || 800,
										height : k.data("height") || "auto",
										icon : k.data("icon") || "?",
										title : k.data("title") || k.attr("title") || k.text(),
										name : k.data("name") || "modalIframe",
										cssClass : k.data("class"),
										headerless : k.data("headerless") || false,
										center : k.data("center") || true
									};
									if (h.icon == "?") {
										var l = k.find("[class^='icon-']");
										h.icon = l.length ? l.attr("class").substring(5) : "file-text"
									}
									d($.extend(h, f))
								} else {
									b();
									$.get(j, function(p) {
												var q = $("#ajaxModal");
												if (p.indexOf("modal-dialog") < 0) {
													p = "<div class='modal-dialog modal-ajax' style='width: {width};'><div class='modal-content'><div class='modal-header'><button class='close' data-dismiss='modal'>×</button><h4 class='modal-title'><i class='icon-{icon}'></i> {title}</h4></div><div class='modal-body' style='height:{height}'>{content}</div></div></div>".format($.extend({
																content : p
															}, h))
												}
												q.html(p);
												if (k.data("width")) {
													var o = parseInt(k.data("width"));
													$(this).data("width", o).find(".modal-dialog").css("width", o);
													e()
												}
												q.modal("show")
											})
								}
								$("#ajaxModal").attr("rel", j);
								return false
							})
				})
	};
	function d(j) {
		var g = {
			width : 800,
			height : "auto",
			icon : "?",
			title : "",
			name : "modalIframe",
			cssClass : "",
			headerless : false,
			waittime : 0,
			center : true
		};
		if (typeof(j) == "string") {
			g.url = j
		} else {
			g = $.extend(g, j)
		}
		b(g);
		if (isNum(g.height.toString())) {
			g.height += "px"
		}
		if (isNum(g.width.toString())) {
			g.width += "px"
		}
		if (g.size == "fullscreen") {
			var l = $(window);
			g.width = l.width();
			g.height = l.height();
			g.cssClass += " fullscreen"
		}
		if (g.headerless) {
			g.cssClass += " hide-header"
		}
		var k = $("#ajaxModal").addClass("modal-loading").data("first", true);
		k.html("<div class='icon-spinner icon-spin loader'></div><div class='modal-dialog modal-iframe' style='width: {width};'><div class='modal-content'><div class='modal-header'><button class='close' data-dismiss='modal'>×</button><h4 class='modal-title'><i class='icon-{icon}'></i> {title}</h4></div><div class='modal-body' style='height:{height}'><iframe id='{name}' name='{name}' src='{url}' frameborder='no' allowtransparency='true' scrolling='auto' hidefocus='' style='width: 100%; height: 100%; left: 0px;'></iframe></div></div></div>".format(g));
		var f = k.find(".modal-body"), h = k.find(".modal-dialog");
		if (g.cssClass) {
			h.addClass(g.cssClass)
		}
		if (g.waittime > 0) {
			g.waitingFuc = setTimeout(function() {
						c(g, k, f, h)
					}, g.waittime)
		}
		var m = document.getElementById(g.name);
		m.onload = m.onreadystatechange = function() {
			if (this.readyState && this.readyState != "complete") {
				return
			}
			if (k.data("first") && (!k.hasClass("modal-loading"))) {
				return
			}
			if (!k.data("first")) {
				k.addClass("modal-loading")
			}
			if (g.waittime > 0) {
				clearTimeout(g.waitingFuc)
			}
			c(g, k, f, h)
		};
		k.modal("show")
	}
	function c(h, k, g, j) {
		g.css("height", h.height - k.find(".modal-header").outerHeight());
		try {
			var f = window.frames[h.name].$;
			if (f("#titlebar").length) {
				k.addClass("with-titlebar");
				if (h.size == "fullscreen") {
					g.css("height", h.height)
				}
			}
			if (h.height == "auto") {
				var l = f("body");
				setTimeout(function() {
							k.removeClass("fade");
							var n = l.addClass("body-modal").outerHeight();
							f("#titlebar > .heading a").each(function() {
										var o = f(this);
										o.replaceWith("<strong class='heading-title'>" + o.text() + "</strong>")
									});
							if (typeof n == "object") {
								n = l.height()
							}
							g.css("height", n);
							e();
							if (k.data("first")) {
								k.data("first", false)
							}
							k.removeClass("modal-loading").addClass("fade")
						}, 100);
				l.resize(function() {
							var n = l.outerHeight();
							if (typeof n == "object") {
								n = l.height()
							}
							g.css("height", n);
							e()
						})
			} else {
				k.removeClass("modal-loading")
			}
			if (f) {
				f.extend({
							closeModal : $.closeModal
						})
			}
		} catch (m) {
			k.removeClass("modal-loading")
		}
	}
	function b(f) {
		if ($("#ajaxModal").length) {
			$("#ajaxModal").attr("class", "modal fade").off("show.bs.modal shown.bs.modal hide.bs.modal hidden.bs.modal")
		} else {
			$('<div id="ajaxModal" class="modal fade"></div>').appendTo("body")
		}
		$ajaxModal = $("#ajaxModal");
		$ajaxModal.data("cancel-reload", false);
		$.extend({
					closeModal : function(h, g) {
						$ajaxModal.modal("hide");
						$ajaxModal.on("hidden.bs.modal", function() {
									if (g && (!$ajaxModal.data("cancel-reload"))) {
										if (g == "this") {
											window.location.reload()
										} else {
											window.location = g
										}
									}
									if (h && $.isFunction(h)) {
										h()
									}
								})
					},
					cancelReloadCloseModal : function() {
						$ajaxModal.data("cancel-reload", true)
					}
				});
		if (!f) {
			return
		}
		if (f.afterShow && $.isFunction(f.afterShow)) {
			$ajaxModal.on("show.bs.modal", f.afterShow)
		}
		if (f.afterShown && $.isFunction(f.afterShown)) {
			$ajaxModal.on("shown.bs.modal", f.afterShown)
		}
		if (f.afterHide && $.isFunction(f.afterHide)) {
			$ajaxModal.on("hide.bs.modal", f.afterHide)
		}
		if (f.afterHidden && $.isFunction(f.afterHidden)) {
			$ajaxModal.on("hidden.bs.modal", f.afterHidden)
		}
	}
	function e(f, g) {
		f = f || "fit";
		if (!g) {
			g = $("#ajaxModal .modal-dialog")
		}
		if (f) {
			var h = Math.max(0, ($(window).height() - g.outerHeight()) / 2);
			var j = f == "fit" ? (h * 2 / 3) : (f == "center" ? h : f);
			g.css("margin-top", j)
		}
	}
	$.extend({
				ajustModalPosition : e,
				modalTrigger : d,
				colorbox : function(f) {
					if ((typeof f == "object") && f.iframe) {
						$.modalTrigger({
									type : "iframe",
									width : f.width,
									afterHide : f.onCleanup,
									url : f.href
								})
					}
				}
			});
	$("[data-toggle=modal], a.iframe").modalTrigger();
	jQuery.fn.colorbox = function(f) {
		if ((typeof f == "object") && f.iframe) {
			$(this).modalTrigger({
						type : "iframe",
						width : f.width,
						afterHide : f.onCleanup,
						url : f.href
					})
		}
	}
}
function setModal4List(c, d, e, b) {
	if (typeof(b) == "undefined") {
		b = 900
	}
	$("." + c).modalTrigger({
				width : b,
				type : "iframe",
				afterHide : function() {
					var f = $.cookie("selfClose");
					if (f != 1) {
						return
					}
					saveWindowSize();
					if (typeof(d) == "string" && d.length > 0) {
						$.cancelReloadCloseModal();
						var g = self.location.href;
						$("#" + d).wrap("<div id='tmpDiv'></div>");
						$("#tmpDiv").load(g + " #" + d, function() {
									$("#tmpDiv").replaceWith($("#tmpDiv").html());
									setModal4List(c, d, e, b);
									$("#" + d + " tbody tr:not(.active-disabled) td").click(function() {
												$(this).closest("tr").toggleClass("active")
											});
									$("#" + d).find("[data-toggle=modal], a.iframe").modalTrigger();
									try {
										$(".date").datetimepicker(datepickerOptions)
									} catch (h) {
									}
									if (typeof(e) == "function") {
										e()
									}
									$.cookie("selfClose", 0)
								})
					}
				}
			})
}
function setTableBehavior() {
	$("#wrap .table:not(.table-data, .table-form, .active-disabled) tbody tr:not(.active-disabled) td").click(function() {
				$(this).closest("tr").toggleClass("active")
			});
	$("#wrap .outer > .table, #wrap .outer > form > .table, #wrap .outer > .mian > .table, #wrap .outer > .mian > form > .table, #wrap .outer > .container > .table").not(".table-data, .table-form, .table-custom").addClass("table table-condensed table-hover table-striped tablesorter")
}
needPing = true;
$(document).ready(function() {
			$("body").addClass("m-{currentModule}-{currentMethod}".format(config));
			setModal();
			setTableBehavior();
			setForm();
			saveWindowSize();
			setSearchBox();
			setOuterBox();
			setRequiredFields();
			setPlaceholder();
			setModalTriggerLink();
			autoCheck();
			toggleSearch();
			$(window).resize(saveWindowSize);
			if (needPing) {
				setTimeout("setPing()", 1000 * 60)
			}
			$(".export").bind("click", function() {
						var b = "";
						$(":checkbox").each(function() {
									if ($(this).attr("checked")) {
										var c = parseInt($(this).val());
										if (c != 0) {
											b = b + c + ","
										}
									}
								});
						if (b != "") {
							b = b.substring(0, b.length - 1)
						}
						$.cookie("checkedItem", b, {
									expires : config.cookieLife,
									path : config.webRoot
								})
					})
		});
$(document).bind("keydown", "Ctrl+g", function(b) {
			$("#searchQuery").attr("value", "");
			$("#searchQuery").focus();
			b.stopPropagation();
			b.preventDefault();
			return false
		});
$(document).bind("keydown", "left", function(b) {
			preLink = ($("#pre").attr("href"));
			if (typeof(preLink) != "undefined") {
				location.href = preLink
			}
		});
$(document).bind("keydown", "right", function(b) {
			nextLink = ($("#next").attr("href"));
			if (typeof(nextLink) != "undefined") {
				location.href = nextLink
			}
		});
!function() {
	var g, f, k, h, e, l = {}.hasOwnProperty, j = function(m, c) {
		function o() {
			this.constructor = m
		}
		for (var n in c) {
			l.call(c, n) && (m[n] = c[n])
		}
		return o.prototype = c.prototype, m.prototype = new o, m.__super__ = c.prototype, m
	};
	h = function() {
		function b() {
			this.options_index = 0, this.parsed = []
		}
		return b.prototype.add_node = function(c) {
			return "OPTGROUP" === c.nodeName.toUpperCase() ? this.add_group(c) : this.add_option(c)
		}, b.prototype.add_group = function(n) {
			var m, s, r, q, p, o;
			for (m = this.parsed.length, this.parsed.push({
						array_index : m,
						group : !0,
						label : this.escapeExpression(n.label),
						children : 0,
						disabled : n.disabled
					}), p = n.childNodes, o = [], r = 0, q = p.length; q > r; r++) {
				s = p[r], o.push(this.add_option(s, m, n.disabled))
			}
			return o
		}, b.prototype.add_option = function(m, d, n) {
			return "OPTION" === m.nodeName.toUpperCase() ? ("" !== m.text ? (null != d && (this.parsed[d].children += 1), this.parsed.push({
						array_index : this.parsed.length,
						options_index : this.options_index,
						value : m.value,
						text : m.text,
						html : m.innerHTML,
						selected : m.selected,
						disabled : n === !0 ? n : m.disabled,
						group_array_index : d,
						classes : m.className,
						style : m.style.cssText
					})) : this.parsed.push({
						array_index : this.parsed.length,
						options_index : this.options_index,
						empty : !0
					}), this.options_index += 1) : void 0
		}, b.prototype.escapeExpression = function(m) {
			var d, n;
			return null == m || m === !1 ? "" : /[\&\<\>\"\'\`]/.test(m) ? (d = {
				"<" : "&lt;",
				">" : "&gt;",
				'"' : "&quot;",
				"'" : "&#x27;",
				"`" : "&#x60;"
			}, n = /&(?!\w+;)|[\<\>\"\'\`]/g, m.replace(n, function(c) {
						return d[c] || "&amp;"
					})) : m
		}, b
	}(), h.select_to_array = function(n) {
		var m, r, q, p, o;
		for (r = new h, o = n.childNodes, q = 0, p = o.length; p > q; q++) {
			m = o[q], r.add_node(m)
		}
		return r.parsed
	}, f = function() {
		function b(d, c) {
			this.form_field = d, this.options = null != c ? c : {}, b.browser_is_supported() && (this.is_multiple = this.form_field.multiple, this.set_default_text(), this.set_default_values(), this.setup(), this.set_up_html(), this.register_observers())
		}
		return b.prototype.set_default_values = function() {
			var c = this;
			return this.click_test_action = function(d) {
				return c.test_active_click(d)
			}, this.activate_action = function(d) {
				return c.activate_field(d)
			}, this.active_field = !1, this.mouse_on_container = !1, this.results_showing = !1, this.result_highlighted = null, this.allow_single_deselect = null != this.options.allow_single_deselect && null != this.form_field.options[0] && "" === this.form_field.options[0].text ? this.options.allow_single_deselect : !1, this.disable_search_threshold = this.options.disable_search_threshold || 0, this.disable_search = this.options.disable_search || !1, this.enable_split_word_search = null != this.options.enable_split_word_search ? this.options.enable_split_word_search : !0, this.group_search = null != this.options.group_search ? this.options.group_search : !0, this.search_contains = this.options.search_contains || !1, this.single_backstroke_delete = null != this.options.single_backstroke_delete
					? this.options.single_backstroke_delete
					: !0, this.max_selected_options = this.options.max_selected_options || 1 / 0, this.inherit_select_classes = this.options.inherit_select_classes || !1, this.display_selected_options = null != this.options.display_selected_options ? this.options.display_selected_options : !0, this.display_disabled_options = null != this.options.display_disabled_options ? this.options.display_disabled_options : !0
		}, b.prototype.set_default_text = function() {
			return this.default_text = this.form_field.getAttribute("data-placeholder") ? this.form_field.getAttribute("data-placeholder") : this.is_multiple ? this.options.placeholder_text_multiple || this.options.placeholder_text || b.default_multiple_text : this.options.placeholder_text_single || this.options.placeholder_text || b.default_single_text, this.results_none_found = this.form_field.getAttribute("data-no_results_text") || this.options.no_results_text || b.default_no_result_text
		}, b.prototype.mouse_enter = function() {
			return this.mouse_on_container = !0
		}, b.prototype.mouse_leave = function() {
			return this.mouse_on_container = !1
		}, b.prototype.input_focus = function() {
			var c = this;
			if (this.is_multiple) {
				if (!this.active_field) {
					return setTimeout(function() {
								return c.container_mousedown()
							}, 50)
				}
			} else {
				if (!this.active_field) {
					return this.activate_field()
				}
			}
		}, b.prototype.input_blur = function() {
			var c = this;
			return this.mouse_on_container ? void 0 : (this.active_field = !1, setTimeout(function() {
						return c.blur_test()
					}, 100))
		}, b.prototype.results_option_build = function(n) {
			var m, r, q, p, o;
			for (m = "", o = this.results_data, q = 0, p = o.length; p > q; q++) {
				r = o[q], m += r.group ? this.result_add_group(r) : this.result_add_option(r), (null != n ? n.first : void 0) && (r.selected && this.is_multiple ? this.choice_build(r) : r.selected && !this.is_multiple && this.single_set_selected_text(r.text))
			}
			return m
		}, b.prototype.result_add_option = function(m) {
			var d, n;
			return m.search_match ? this.include_option_in_results(m) ? (d = [], m.disabled || m.selected && this.is_multiple || d.push("active-result"), !m.disabled || m.selected && this.is_multiple || d.push("disabled-result"), m.selected && d.push("result-selected"), null != m.group_array_index && d.push("group-option"), "" !== m.classes && d.push(m.classes), n = document.createElement("li"), n.className = d.join(" "), n.style.cssText = m.style, n.setAttribute("data-option-array-index", m.array_index), n.innerHTML = m.search_text, this.outerHTML(n)) : "" : ""
		}, b.prototype.result_add_group = function(d) {
			var c;
			return d.search_match || d.group_match ? d.active_options > 0 ? (c = document.createElement("li"), c.className = "group-result", c.innerHTML = d.search_text, this.outerHTML(c)) : "" : ""
		}, b.prototype.results_update_field = function() {
			return this.set_default_text(), this.is_multiple || this.results_reset_cleanup(), this.result_clear_highlight(), this.results_build(), this.results_showing ? this.winnow_results() : void 0
		}, b.prototype.reset_single_select_options = function() {
			var n, m, q, p, o;
			for (p = this.results_data, o = [], m = 0, q = p.length; q > m; m++) {
				n = p[m], n.selected ? o.push(n.selected = !1) : o.push(void 0)
			}
			return o
		}, b.prototype.results_toggle = function() {
			return this.results_showing ? this.results_hide() : this.results_show()
		}, b.prototype.results_search = function() {
			return this.results_showing ? this.winnow_results() : this.results_show()
		}, b.prototype.winnow_results = function() {
			var z, y, x, w, v, u, t, s, r, q, p, o, n;
			for (this.no_results_clear(), v = 0, t = this.get_search_text(), z = t.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&"), w = this.search_contains ? "" : "^", x = new RegExp(w + z, "i"), q = new RegExp(z, "i"), n = this.results_data, p = 0, o = n.length; o > p; p++) {
				y = n[p], y.search_match = !1, u = null, this.include_option_in_results(y)
						&& (y.group && (y.group_match = !1, y.active_options = 0), null != y.group_array_index && this.results_data[y.group_array_index] && (u = this.results_data[y.group_array_index], 0 === u.active_options && u.search_match && (v += 1), u.active_options += 1), (!y.group || this.group_search)
								&& (y.search_text = y.group ? y.label : y.html, y.search_match = this.search_string_match(y.search_text, x), y.search_match && !y.group && (v += 1), y.search_match ? (t.length && (s = y.search_text.search(q), r = y.search_text.substr(0, s + t.length) + "</em>" + y.search_text.substr(s + t.length), y.search_text = r.substr(0, s) + "<em>" + r.substr(s)), null != u && (u.group_match = !0)) : null != y.group_array_index && this.results_data[y.group_array_index].search_match && (y.search_match = !0)))
			}
			return this.result_clear_highlight(), 1 > v && t.length ? (this.update_results_content(""), this.no_results(t)) : (this.update_results_content(this.results_option_build()), this.winnow_results_set_highlight())
		}, b.prototype.search_string_match = function(n, m) {
			var r, q, p, o;
			if (m.test(n)) {
				return !0
			}
			if (this.enable_split_word_search && (n.indexOf(" ") >= 0 || 0 === n.indexOf("[")) && (q = n.replace(/\[|\]/g, "").split(" "), q.length)) {
				for (p = 0, o = q.length; o > p; p++) {
					if (r = q[p], m.test(r)) {
						return !0
					}
				}
			}
		}, b.prototype.choices_count = function() {
			var n, m, p, o;
			if (null != this.selected_option_count) {
				return this.selected_option_count
			}
			for (this.selected_option_count = 0, o = this.form_field.options, m = 0, p = o.length; p > m; m++) {
				n = o[m], n.selected && (this.selected_option_count += 1)
			}
			return this.selected_option_count
		}, b.prototype.choices_click = function(c) {
			return c.preventDefault(), this.results_showing || this.is_disabled ? void 0 : this.results_show()
		}, b.prototype.keyup_checker = function(m) {
			var d, n;
			switch (d = null != (n = m.which) ? n : m.keyCode, this.search_field_scale(), d) {
				case 8 :
					if (this.is_multiple && this.backstroke_length < 1 && this.choices_count() > 0) {
						return this.keydown_backstroke()
					}
					if (!this.pending_backstroke) {
						return this.result_clear_highlight(), this.results_search()
					}
					break;
				case 13 :
					if (m.preventDefault(), this.results_showing) {
						return this.result_select(m)
					}
					break;
				case 27 :
					return this.results_showing && this.results_hide(), !0;
				case 9 :
				case 38 :
				case 40 :
				case 16 :
				case 91 :
				case 17 :
					break;
				default :
					return this.results_search()
			}
		}, b.prototype.clipboard_event_checker = function() {
			var c = this;
			return setTimeout(function() {
						return c.results_search()
					}, 50)
		}, b.prototype.container_width = function() {
			return null != this.options.width ? this.options.width : "" + this.form_field.offsetWidth + "px"
		}, b.prototype.include_option_in_results = function(c) {
			return this.is_multiple && !this.display_selected_options && c.selected ? !1 : !this.display_disabled_options && c.disabled ? !1 : c.empty ? !1 : !0
		}, b.prototype.search_results_touchstart = function(c) {
			return this.touch_started = !0, this.search_results_mouseover(c)
		}, b.prototype.search_results_touchmove = function(c) {
			return this.touch_started = !1, this.search_results_mouseout(c)
		}, b.prototype.search_results_touchend = function(c) {
			return this.touch_started ? this.search_results_mouseup(c) : void 0
		}, b.prototype.outerHTML = function(d) {
			var c;
			return d.outerHTML ? d.outerHTML : (c = document.createElement("div"), c.appendChild(d), c.innerHTML)
		}, b.browser_is_supported = function() {
			return "Microsoft Internet Explorer" === window.navigator.appName ? document.documentMode >= 8 : /iP(od|hone)/i.test(window.navigator.userAgent) ? !1 : /Android/i.test(window.navigator.userAgent) && /Mobile/i.test(window.navigator.userAgent) ? !1 : !0
		}, b.default_multiple_text = "Select Some Options", b.default_single_text = "Select an Option", b.default_no_result_text = "No results match", b
	}(), g = jQuery, g.fn.extend({
				chosen : function(c) {
					return f.browser_is_supported() ? this.each(function() {
								var m, b;
								m = g(this), b = m.data("chosen"), "destroy" === c && b ? b.destroy() : b || m.data("chosen", new k(this, c))
							}) : this
				}
			}), k = function(d) {
		function b() {
			return e = b.__super__.constructor.apply(this, arguments)
		}
		return j(b, d), b.prototype.setup = function() {
			return this.form_field_jq = g(this.form_field), this.current_selectedIndex = this.form_field.selectedIndex, this.is_rtl = this.form_field_jq.hasClass("chosen-rtl")
		}, b.prototype.set_up_html = function() {
			var m, n;
			return m = ["chosen-container"], m.push("chosen-container-" + (this.is_multiple ? "multi" : "single")), this.inherit_select_classes && this.form_field.className && m.push(this.form_field.className), this.is_rtl && m.push("chosen-rtl"), n = {
				"class" : m.join(" "),
				style : "width: " + this.container_width() + ";",
				title : this.form_field.title
			}, this.form_field.id.length && (n.id = this.form_field.id.replace(/[^\w]/g, "_") + "_chosen"), this.container = g("<div />", n), this.is_multiple ? this.container.html('<ul class="chosen-choices"><li class="search-field"><input type="text" value="' + this.default_text + '" class="default" autocomplete="off" style="width:25px;" /></li></ul><div class="chosen-drop"><ul class="chosen-results"></ul></div>') : this.container.html('<a class="chosen-single chosen-default" tabindex="-1"><span>' + this.default_text + '</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off" /></div><ul class="chosen-results"></ul></div>'), this.form_field_jq.hide().after(this.container), this.dropdown = this.container
					.find("div.chosen-drop").first(), this.search_field = this.container.find("input").first(), this.search_results = this.container.find("ul.chosen-results").first(), this.search_field_scale(), this.search_no_results = this.container.find("li.no-results").first(), this.is_multiple ? (this.search_choices = this.container.find("ul.chosen-choices").first(), this.search_container = this.container.find("li.search-field").first()) : (this.search_container = this.container.find("div.chosen-search").first(), this.selected_item = this.container.find(".chosen-single").first()), this.results_build(), this.set_tab_index(), this.set_label_behavior(), this.form_field_jq.trigger("chosen:ready", {
						chosen : this
					})
		}, b.prototype.register_observers = function() {
			var c = this;
			return this.container.bind("mousedown.chosen", function(m) {
						c.container_mousedown(m)
					}), this.container.bind("mouseup.chosen", function(m) {
						c.container_mouseup(m)
					}), this.container.bind("mouseenter.chosen", function(m) {
						c.mouse_enter(m)
					}), this.container.bind("mouseleave.chosen", function(m) {
						c.mouse_leave(m)
					}), this.search_results.bind("mouseup.chosen", function(m) {
						c.search_results_mouseup(m)
					}), this.search_results.bind("mouseover.chosen", function(m) {
						c.search_results_mouseover(m)
					}), this.search_results.bind("mouseout.chosen", function(m) {
						c.search_results_mouseout(m)
					}), this.search_results.bind("mousewheel.chosen DOMMouseScroll.chosen", function(m) {
						c.search_results_mousewheel(m)
					}), this.search_results.bind("touchstart.chosen", function(m) {
						c.search_results_touchstart(m)
					}), this.search_results.bind("touchmove.chosen", function(m) {
						c.search_results_touchmove(m)
					}), this.search_results.bind("touchend.chosen", function(m) {
						c.search_results_touchend(m)
					}), this.form_field_jq.bind("chosen:updated.chosen", function(m) {
						c.results_update_field(m)
					}), this.form_field_jq.bind("chosen:activate.chosen", function(m) {
						c.activate_field(m)
					}), this.form_field_jq.bind("chosen:open.chosen", function(m) {
						c.container_mousedown(m)
					}), this.form_field_jq.bind("chosen:close.chosen", function(m) {
						c.input_blur(m)
					}), this.search_field.bind("blur.chosen", function(m) {
						c.input_blur(m)
					}), this.search_field.bind("keyup.chosen", function(m) {
						c.keyup_checker(m)
					}), this.search_field.bind("keydown.chosen", function(m) {
						c.keydown_checker(m)
					}), this.search_field.bind("focus.chosen", function(m) {
						c.input_focus(m)
					}), this.search_field.bind("cut.chosen", function(m) {
						c.clipboard_event_checker(m)
					}), this.search_field.bind("paste.chosen", function(m) {
						c.clipboard_event_checker(m)
					}), this.is_multiple ? this.search_choices.bind("click.chosen", function(m) {
						c.choices_click(m)
					}) : this.container.bind("click.chosen", function(m) {
						m.preventDefault()
					})
		}, b.prototype.destroy = function() {
			return g(this.container[0].ownerDocument).unbind("click.chosen", this.click_test_action), this.search_field[0].tabIndex && (this.form_field_jq[0].tabIndex = this.search_field[0].tabIndex), this.container.remove(), this.form_field_jq.removeData("chosen"), this.form_field_jq.show()
		}, b.prototype.search_field_disabled = function() {
			return this.is_disabled = this.form_field_jq[0].disabled, this.is_disabled ? (this.container.addClass("chosen-disabled"), this.search_field[0].disabled = !0, this.is_multiple || this.selected_item.unbind("focus.chosen", this.activate_action), this.close_field()) : (this.container.removeClass("chosen-disabled"), this.search_field[0].disabled = !1, this.is_multiple ? void 0 : this.selected_item.bind("focus.chosen", this.activate_action))
		}, b.prototype.container_mousedown = function(c) {
			return this.is_disabled || (c && "mousedown" === c.type && !this.results_showing && c.preventDefault(), null != c && g(c.target).hasClass("search-choice-close")) ? void 0 : (this.active_field ? this.is_multiple || !c || g(c.target)[0] !== this.selected_item[0] && !g(c.target).parents("a.chosen-single").length || (c.preventDefault(), this.results_toggle()) : (this.is_multiple && this.search_field.val(""), g(this.container[0].ownerDocument).bind("click.chosen", this.click_test_action), this.results_show()), this.activate_field())
		}, b.prototype.container_mouseup = function(c) {
			return "ABBR" !== c.target.nodeName || this.is_disabled ? void 0 : this.results_reset(c)
		}, b.prototype.search_results_mousewheel = function(m) {
			var c;
			return m.originalEvent && (c = -m.originalEvent.wheelDelta || m.originalEvent.detail), null != c ? (m.preventDefault(), "DOMMouseScroll" === m.type && (c = 40 * c), this.search_results.scrollTop(c + this.search_results.scrollTop())) : void 0
		}, b.prototype.blur_test = function() {
			return !this.active_field && this.container.hasClass("chosen-container-active") ? this.close_field() : void 0
		}, b.prototype.close_field = function() {
			return g(this.container[0].ownerDocument).unbind("click.chosen", this.click_test_action), this.active_field = !1, this.results_hide(), this.container.removeClass("chosen-container-active"), this.clear_backstroke(), this.show_search_field_default(), this.search_field_scale()
		}, b.prototype.activate_field = function() {
			return this.container.addClass("chosen-container-active"), this.active_field = !0, this.search_field.val(this.search_field.val()), this.search_field.focus()
		}, b.prototype.test_active_click = function(m) {
			var n;
			return n = g(m.target).closest(".chosen-container"), n.length && this.container[0] === n[0] ? this.active_field = !0 : this.close_field()
		}, b.prototype.results_build = function() {
			return this.parsing = !0, this.selected_option_count = null, this.results_data = h.select_to_array(this.form_field), this.is_multiple ? this.search_choices.find("li.search-choice").remove() : this.is_multiple || (this.single_set_selected_text(), this.disable_search || this.form_field.options.length <= this.disable_search_threshold ? (this.search_field[0].readOnly = !0, this.container.addClass("chosen-container-single-nosearch")) : (this.search_field[0].readOnly = !1, this.container.removeClass("chosen-container-single-nosearch"))), this.update_results_content(this.results_option_build({
						first : !0
					})), this.search_field_disabled(), this.show_search_field_default(), this.search_field_scale(), this.parsing = !1
		}, b.prototype.result_do_highlight = function(n) {
			var m, r, q, p, o;
			if (n.length) {
				if (this.result_clear_highlight(), this.result_highlight = n, this.result_highlight.addClass("highlighted"), q = parseInt(this.search_results.css("maxHeight"), 10), o = this.search_results.scrollTop(), p = q + o, r = this.result_highlight.position().top + this.search_results.scrollTop(), m = r + this.result_highlight.outerHeight(), m >= p) {
					return this.search_results.scrollTop(m - q > 0 ? m - q : 0)
				}
				if (o > r) {
					return this.search_results.scrollTop(r)
				}
			}
		}, b.prototype.result_clear_highlight = function() {
			return this.result_highlight && this.result_highlight.removeClass("highlighted"), this.result_highlight = null
		}, b.prototype.results_show = function() {
			return this.is_multiple && this.max_selected_options <= this.choices_count() ? (this.form_field_jq.trigger("chosen:maxselected", {
						chosen : this
					}), !1) : (this.container.addClass("chosen-with-drop"), this.results_showing = !0, this.search_field.focus(), this.search_field.val(this.search_field.val()), this.winnow_results(), this.form_field_jq.trigger("chosen:showing_dropdown", {
						chosen : this
					}))
		}, b.prototype.update_results_content = function(c) {
			return this.search_results.html(c)
		}, b.prototype.results_hide = function() {
			return this.results_showing && (this.result_clear_highlight(), this.container.removeClass("chosen-with-drop"), this.form_field_jq.trigger("chosen:hiding_dropdown", {
						chosen : this
					})), this.results_showing = !1
		}, b.prototype.set_tab_index = function() {
			var c;
			return this.form_field.tabIndex ? (c = this.form_field.tabIndex, this.form_field.tabIndex = -1, this.search_field[0].tabIndex = c) : void 0
		}, b.prototype.set_label_behavior = function() {
			var c = this;
			return this.form_field_label = this.form_field_jq.parents("label"), !this.form_field_label.length && this.form_field.id.length && (this.form_field_label = g("label[for='" + this.form_field.id + "']")), this.form_field_label.length > 0 ? this.form_field_label.bind("click.chosen", function(m) {
						return c.is_multiple ? c.container_mousedown(m) : c.activate_field()
					}) : void 0
		}, b.prototype.show_search_field_default = function() {
			return this.is_multiple && this.choices_count() < 1 && !this.active_field ? (this.search_field.val(this.default_text), this.search_field.addClass("default")) : (this.search_field.val(""), this.search_field.removeClass("default"))
		}, b.prototype.search_results_mouseup = function(m) {
			var n;
			return n = g(m.target).hasClass("active-result") ? g(m.target) : g(m.target).parents(".active-result").first(), n.length ? (this.result_highlight = n, this.result_select(m), this.search_field.focus()) : void 0
		}, b.prototype.search_results_mouseover = function(m) {
			var n;
			return n = g(m.target).hasClass("active-result") ? g(m.target) : g(m.target).parents(".active-result").first(), n ? this.result_do_highlight(n) : void 0
		}, b.prototype.search_results_mouseout = function(c) {
			return g(c.target).hasClass("active-result") ? this.result_clear_highlight() : void 0
		}, b.prototype.choice_build = function(m) {
			var p, o, n = this;
			return p = g("<li />", {
						"class" : "search-choice"
					}).html("<span>" + m.html + "</span>"), m.disabled ? p.addClass("search-choice-disabled") : (o = g("<a />", {
						"class" : "search-choice-close",
						"data-option-array-index" : m.array_index
					}), o.bind("click.chosen", function(c) {
						return n.choice_destroy_link_click(c)
					}), p.append(o)), this.search_container.before(p)
		}, b.prototype.choice_destroy_link_click = function(c) {
			return c.preventDefault(), c.stopPropagation(), this.is_disabled ? void 0 : this.choice_destroy(g(c.target))
		}, b.prototype.choice_destroy = function(c) {
			return this.result_deselect(c[0].getAttribute("data-option-array-index")) ? (this.show_search_field_default(), this.is_multiple && this.choices_count() > 0 && this.search_field.val().length < 1 && this.results_hide(), c.parents("li").first().remove(), this.search_field_scale()) : void 0
		}, b.prototype.results_reset = function() {
			return this.reset_single_select_options(), this.form_field.options[0].selected = !0, this.single_set_selected_text(), this.show_search_field_default(), this.results_reset_cleanup(), this.form_field_jq.trigger("change"), this.active_field ? this.results_hide() : void 0
		}, b.prototype.results_reset_cleanup = function() {
			return this.current_selectedIndex = this.form_field.selectedIndex, this.selected_item.find("abbr").remove()
		}, b.prototype.result_select = function(n) {
			var m, o;
			return this.result_highlight ? (m = this.result_highlight, this.result_clear_highlight(), this.is_multiple && this.max_selected_options <= this.choices_count() ? (this.form_field_jq.trigger("chosen:maxselected", {
						chosen : this
					}), !1) : (this.is_multiple ? m.removeClass("active-result") : this.reset_single_select_options(), o = this.results_data[m[0].getAttribute("data-option-array-index")], o.selected = !0, this.form_field.options[o.options_index].selected = !0, this.selected_option_count = null, this.is_multiple ? this.choice_build(o) : this.single_set_selected_text(o.text), (n.metaKey || n.ctrlKey) && this.is_multiple || this.results_hide(), this.search_field.val(""), (this.is_multiple || this.form_field.selectedIndex !== this.current_selectedIndex) && this.form_field_jq.trigger("change", {
						selected : this.form_field.options[o.options_index].value
					}), this.current_selectedIndex = this.form_field.selectedIndex, this.search_field_scale())) : void 0
		}, b.prototype.single_set_selected_text = function(c) {
			return null == c && (c = this.default_text), c === this.default_text ? this.selected_item.addClass("chosen-default") : (this.single_deselect_control_build(), this.selected_item.removeClass("chosen-default")), this.selected_item.find("span").text(c)
		}, b.prototype.result_deselect = function(m) {
			var c;
			return c = this.results_data[m], this.form_field.options[c.options_index].disabled ? !1 : (c.selected = !1, this.form_field.options[c.options_index].selected = !1, this.selected_option_count = null, this.result_clear_highlight(), this.results_showing && this.winnow_results(), this.form_field_jq.trigger("change", {
						deselected : this.form_field.options[c.options_index].value
					}), this.search_field_scale(), !0)
		}, b.prototype.single_deselect_control_build = function() {
			return this.allow_single_deselect ? (this.selected_item.find("abbr").length || this.selected_item.find("span").first().after('<abbr class="search-choice-close"></abbr>'), this.selected_item.addClass("chosen-single-with-deselect")) : void 0
		}, b.prototype.get_search_text = function() {
			return this.search_field.val() === this.default_text ? "" : g("<div/>").text(g.trim(this.search_field.val())).html()
		}, b.prototype.winnow_results_set_highlight = function() {
			var m, c;
			return c = this.is_multiple ? [] : this.search_results.find(".result-selected.active-result"), m = c.length ? c.first() : this.search_results.find(".active-result").first(), null != m ? this.result_do_highlight(m) : void 0
		}, b.prototype.no_results = function(m) {
			var n;
			return n = g('<li class="no-results">' + this.results_none_found + ' "<span></span>"</li>'), n.find("span").first().html(m), this.search_results.append(n), this.form_field_jq.trigger("chosen:no_results", {
						chosen : this
					})
		}, b.prototype.no_results_clear = function() {
			return this.search_results.find(".no-results").remove()
		}, b.prototype.keydown_arrow = function() {
			var c;
			return this.results_showing && this.result_highlight ? (c = this.result_highlight.nextAll("li.active-result").first()) ? this.result_do_highlight(c) : void 0 : this.results_show()
		}, b.prototype.keyup_arrow = function() {
			var c;
			return this.results_showing || this.is_multiple ? this.result_highlight ? (c = this.result_highlight.prevAll("li.active-result"), c.length ? this.result_do_highlight(c.first()) : (this.choices_count() > 0 && this.results_hide(), this.result_clear_highlight())) : void 0 : this.results_show()
		}, b.prototype.keydown_backstroke = function() {
			var c;
			return this.pending_backstroke ? (this.choice_destroy(this.pending_backstroke.find("a").first()), this.clear_backstroke()) : (c = this.search_container.siblings("li.search-choice").last(), c.length && !c.hasClass("search-choice-disabled") ? (this.pending_backstroke = c, this.single_backstroke_delete ? this.keydown_backstroke() : this.pending_backstroke.addClass("search-choice-focus")) : void 0)
		}, b.prototype.clear_backstroke = function() {
			return this.pending_backstroke && this.pending_backstroke.removeClass("search-choice-focus"), this.pending_backstroke = null
		}, b.prototype.keydown_checker = function(n) {
			var m, o;
			switch (m = null != (o = n.which) ? o : n.keyCode, this.search_field_scale(), 8 !== m && this.pending_backstroke && this.clear_backstroke(), m) {
				case 8 :
					this.backstroke_length = this.search_field.val().length;
					break;
				case 9 :
					this.results_showing && !this.is_multiple && this.result_select(n), this.mouse_on_container = !1;
					break;
				case 13 :
					n.preventDefault();
					break;
				case 38 :
					n.preventDefault(), this.keyup_arrow();
					break;
				case 40 :
					n.preventDefault(), this.keydown_arrow()
			}
		}, b.prototype.search_field_scale = function() {
			var u, t, s, r, q, p, o, n, m;
			if (this.is_multiple) {
				for (s = 0, o = 0, q = "position:absolute; left: -1000px; top: -1000px; display:none;", p = ["font-size", "font-style", "font-weight", "font-family", "line-height", "text-transform", "letter-spacing"], n = 0, m = p.length; m > n; n++) {
					r = p[n], q += r + ":" + this.search_field.css(r) + ";"
				}
				return u = g("<div />", {
							style : q
						}), u.text(this.search_field.val()), g("body").append(u), o = u.width() + 25, u.remove(), t = this.container.outerWidth(), o > t - 10 && (o = t - 10), this.search_field.css({
							width : o + "px"
						})
			}
		}, b
	}(f)
}.call(this);
(function(b) {
	b.extend(b.fn, {
				swapClass : function(f, e) {
					var d = this.filter("." + f);
					this.filter("." + e).removeClass(e).addClass(f);
					d.removeClass(f).addClass(e);
					return this
				},
				replaceClass : function(e, d) {
					return this.filter("." + e).removeClass(e).addClass(d).end()
				},
				hoverClass : function(d) {
					d = d || "hover";
					return this.hover(function() {
								b(this).addClass(d)
							}, function() {
								b(this).removeClass(d)
							})
				},
				heightToggle : function(d, e) {
					d ? this.animate({
								height : "toggle"
							}, d, e) : this.each(function() {
								jQuery(this)[jQuery(this).css("display") == "none" ? "show" : "hide"]();
								if (e) {
									e.apply(this, arguments)
								}
							})
				},
				heightHide : function(d, e) {
					if (d) {
						this.animate({
									height : "hide"
								}, d, e)
					} else {
						this.hide();
						if (e) {
							this.each(e)
						}
					}
				},
				prepareBranches : function(d) {
					if (!d.prerendered) {
						this.filter(":last-child:not(ul)").addClass(c.last);
						this.filter((d.collapsed ? "" : "." + c.closed) + ":not(." + c.open + ")").find(">ul").hide()
					}
					return this.filter(":has(>ul)")
				},
				applyClasses : function(d, e) {
					this.filter(":has(>ul):not(:has(>a))").find(">span").click(function(f) {
								e.apply(b(this).next())
							}).add(b("a", this)).hoverClass();
					if (!d.prerendered) {
						this.filter(":has(>ul:hidden)").addClass(c.expandable).replaceClass(c.last, c.lastExpandable);
						this.not(":has(>ul:hidden)").addClass(c.collapsable).replaceClass(c.last, c.lastCollapsable);
						this.prepend('<div class="' + c.hitarea + '"/>').find("div." + c.hitarea).each(function() {
									var f = "";
									b.each(b(this).parent().attr("class").split(" "), function() {
												f += this + "-hitarea "
											});
									b(this).addClass(f)
								})
					}
					this.find("div." + c.hitarea).click(e)
				},
				treeview : function(e) {
					e = b.extend({
								cookieId : "treeview"
							}, e);
					if (e.add) {
						return this.trigger("add", [e.add])
					}
					if (e.toggle) {
						var k = e.toggle;
						e.toggle = function() {
							return k.apply(b(this).parent()[0], arguments)
						}
					}
					function d(n, p) {
						function o(q) {
							return function() {
								g.apply(b("div." + c.hitarea, n).filter(function() {
											return q ? b(this).parent("." + q).length : true
										}));
								return false
							}
						}
						b("a:eq(0)", p).click(o(c.collapsable));
						b("a:eq(1)", p).click(o(c.expandable));
						b("a:eq(2)", p).click(o())
					}
					function g() {
						b(this).parent().find(">.hitarea").swapClass(c.collapsableHitarea, c.expandableHitarea).swapClass(c.lastCollapsableHitarea, c.lastExpandableHitarea).end().swapClass(c.collapsable, c.expandable).swapClass(c.lastCollapsable, c.lastExpandable).find(">ul").heightToggle(e.animated, e.toggle);
						if (e.unique) {
							b(this).parent().siblings().find(">.hitarea").replaceClass(c.collapsableHitarea, c.expandableHitarea).replaceClass(c.lastCollapsableHitarea, c.lastExpandableHitarea).end().replaceClass(c.collapsable, c.expandable).replaceClass(c.lastCollapsable, c.lastExpandable).find(">ul").heightHide(e.animated, e.toggle)
						}
					}
					function m() {
						function o(p) {
							return p ? 1 : 0
						}
						var n = [];
						l.each(function(p, q) {
									n[p] = b(q).is(":has(>ul:visible)") ? 1 : 0
								});
						b.cookie(e.cookieId, n.join(""))
					}
					function f() {
						var n = b.cookie(e.cookieId);
						if (n) {
							var o = n.split("");
							l.each(function(p, q) {
										b(q).find(">ul")[parseInt(o[p]) ? "show" : "hide"]()
									})
						}
					}
					this.addClass("treeview");
					var l = this.find("li").prepareBranches(e);
					switch (e.persist) {
						case "cookie" :
							var j = e.toggle;
							e.toggle = function() {
								m();
								if (j) {
									j.apply(this, arguments)
								}
							};
							f();
							break;
						case "location" :
							var h = this.find("a").filter(function() {
										return this.href.toLowerCase() == location.href.toLowerCase()
									});
							if (h.length) {
								h.addClass("selected").parents("ul, li").add(h.next()).show()
							}
							break
					}
					l.applyClasses(e, g);
					if (e.control) {
						d(this, e.control);
						b(e.control).show()
					}
					return this.bind("add", function(o, n) {
								b(n).prev().removeClass(c.last).removeClass(c.lastCollapsable).removeClass(c.lastExpandable).find(">.hitarea").removeClass(c.lastCollapsableHitarea).removeClass(c.lastExpandableHitarea);
								b(n).find("li").andSelf().prepareBranches(e).applyClasses(e, g)
							})
				}
			});
	var c = b.fn.treeview.classes = {
		open : "open",
		closed : "closed",
		expandable : "expandable",
		expandableHitarea : "expandable-hitarea",
		lastExpandableHitarea : "lastExpandable-hitarea",
		collapsable : "collapsable",
		collapsableHitarea : "collapsable-hitarea",
		lastCollapsableHitarea : "lastCollapsable-hitarea",
		lastCollapsable : "lastCollapsable",
		lastExpandable : "lastExpandable",
		last : "last",
		hitarea : "hitarea"
	};
	b.fn.Treeview = b.fn.treeview
})(jQuery);
!function(g) {
	function d() {
		return new Date(Date.UTC.apply(Date, arguments))
	}
	var c = function(h, e) {
		var j = this;
		this.element = g(h), this.language = e.language || this.element.data("date-language") || "en", this.language = this.language in f ? this.language : "en", this.isRTL = f[this.language].rtl || !1, this.formatType = e.formatType || this.element.data("format-type") || "standard", this.format = b.parseFormat(e.format || this.element.data("date-format") || f[this.language].format || b.getDefaultFormat(this.formatType, "input"), this.formatType), this.isInline = !1, this.isVisible = !1, this.isInput = this.element.is("input"), this.component = this.element.is(".date") ? this.element.find(".input-group-addon .icon-th, .input-group-addon .icon-time, .input-group-addon .icon-calendar").parent() : !1, this.componentReset = this.element.is(".date") ? this.element
				.find(".input-group-addon .icon-remove").parent() : !1, this.hasInput = this.component && this.element.find("input").length, this.component && 0 === this.component.length && (this.component = !1), this.linkField = e.linkField || this.element.data("link-field") || !1, this.linkFormat = b.parseFormat(e.linkFormat || this.element.data("link-format") || b.getDefaultFormat(this.formatType, "link"), this.formatType), this.minuteStep = e.minuteStep || this.element.data("minute-step") || 5, this.pickerPosition = e.pickerPosition || this.element.data("picker-position") || "bottom-right", this.showMeridian = e.showMeridian || this.element.data("show-meridian") || !1, this.initialDate = e.initialDate || new Date, this._attachEvents(), this.formatViewType = "datetime", "formatViewType" in e
				? this.formatViewType = e.formatViewType
				: "formatViewType" in this.element.data() && (this.formatViewType = this.element.data("formatViewType")), this.minView = 0, "minView" in e ? this.minView = e.minView : "minView" in this.element.data() && (this.minView = this.element.data("min-view")), this.minView = b.convertViewMode(this.minView), this.maxView = b.modes.length - 1, "maxView" in e ? this.maxView = e.maxView : "maxView" in this.element.data() && (this.maxView = this.element.data("max-view")), this.maxView = b.convertViewMode(this.maxView), this.wheelViewModeNavigation = !1, "wheelViewModeNavigation" in e ? this.wheelViewModeNavigation = e.wheelViewModeNavigation : "wheelViewModeNavigation" in this.element.data() && (this.wheelViewModeNavigation = this.element.data("view-mode-wheel-navigation")), this.wheelViewModeNavigationInverseDirection = !1, "wheelViewModeNavigationInverseDirection" in e
				? this.wheelViewModeNavigationInverseDirection = e.wheelViewModeNavigationInverseDirection
				: "wheelViewModeNavigationInverseDirection" in this.element.data() && (this.wheelViewModeNavigationInverseDirection = this.element.data("view-mode-wheel-navigation-inverse-dir")), this.wheelViewModeNavigationDelay = 100, "wheelViewModeNavigationDelay" in e ? this.wheelViewModeNavigationDelay = e.wheelViewModeNavigationDelay : "wheelViewModeNavigationDelay" in this.element.data() && (this.wheelViewModeNavigationDelay = this.element.data("view-mode-wheel-navigation-delay")), this.startViewMode = 2, "startView" in e ? this.startViewMode = e.startView : "startView" in this.element.data() && (this.startViewMode = this.element.data("start-view")), this.startViewMode = b.convertViewMode(this.startViewMode), this.viewMode = this.startViewMode, this.viewSelect = this.minView, "viewSelect" in e
				? this.viewSelect = e.viewSelect
				: "viewSelect" in this.element.data() && (this.viewSelect = this.element.data("view-select")), this.viewSelect = b.convertViewMode(this.viewSelect), this.forceParse = !0, "forceParse" in e ? this.forceParse = e.forceParse : "dateForceParse" in this.element.data() && (this.forceParse = this.element.data("date-force-parse")), this.picker = g(b.template).appendTo(this.isInline ? this.element : "body").on({
					click : g.proxy(this.click, this),
					mousedown : g.proxy(this.mousedown, this)
				}), this.wheelViewModeNavigation && (g.fn.mousewheel ? this.picker.on({
					mousewheel : g.proxy(this.mousewheel, this)
				}) : console.log("Mouse Wheel event is not supported. Please include the jQuery Mouse Wheel plugin before enabling this option")), this.isInline ? this.picker.addClass("datetimepicker-inline") : this.picker.addClass("datetimepicker-dropdown-" + this.pickerPosition + " dropdown-menu"), this.isRTL && (this.picker.addClass("datetimepicker-rtl"), this.picker.find(".prev span, .next span").toggleClass("icon-arrow-left icon-arrow-right")), g(document).on("mousedown", function(k) {
					0 === g(k.target).closest(".datetimepicker").length && j.hide()
				}), this.autoclose = !1, "autoclose" in e ? this.autoclose = e.autoclose : "dateAutoclose" in this.element.data() && (this.autoclose = this.element.data("date-autoclose")), this.keyboardNavigation = !0, "keyboardNavigation" in e ? this.keyboardNavigation = e.keyboardNavigation : "dateKeyboardNavigation" in this.element.data() && (this.keyboardNavigation = this.element.data("date-keyboard-navigation")), this.todayBtn = e.todayBtn || this.element.data("date-today-btn") || !1, this.todayHighlight = e.todayHighlight || this.element.data("date-today-highlight") || !1, this.weekStart = (e.weekStart || this.element.data("date-weekstart") || f[this.language].weekStart || 0) % 7, this.weekEnd = (this.weekStart + 6) % 7, this.startDate = -1 / 0, this.endDate = 1 / 0, this.daysOfWeekDisabled = [], this
				.setStartDate(e.startDate || this.element.data("date-startdate")), this.setEndDate(e.endDate || this.element.data("date-enddate")), this.setDaysOfWeekDisabled(e.daysOfWeekDisabled || this.element.data("date-days-of-week-disabled")), this.fillDow(), this.fillMonths(), this.update(), this.showMode(), this.isInline && this.show()
	};
	c.prototype = {
		constructor : c,
		_events : [],
		_attachEvents : function() {
			this._detachEvents(), this.isInput ? this._events = [[this.element, {
						focus : g.proxy(this.show, this),
						keyup : g.proxy(this.update, this),
						keydown : g.proxy(this.keydown, this)
					}]] : this.component && this.hasInput ? (this._events = [[this.element.find("input"), {
								focus : g.proxy(this.show, this),
								keyup : g.proxy(this.update, this),
								keydown : g.proxy(this.keydown, this)
							}], [this.component, {
								click : g.proxy(this.show, this)
							}]], this.componentReset && this._events.push([this.componentReset, {
						click : g.proxy(this.reset, this)
					}])) : this.element.is("div") ? this.isInline = !0 : this._events = [[this.element, {
						click : g.proxy(this.show, this)
					}]];
			for (var h, e, j = 0; j < this._events.length; j++) {
				h = this._events[j][0], e = this._events[j][1], h.on(e)
			}
		},
		_detachEvents : function() {
			for (var k, j, h = 0; h < this._events.length; h++) {
				k = this._events[h][0], j = this._events[h][1], k.off(j)
			}
			this._events = []
		},
		show : function(e) {
			this.picker.show(), this.height = this.component ? this.component.outerHeight() : this.element.outerHeight(), this.forceParse && this.update(), this.place(), g(window).on("resize", g.proxy(this.place, this)), e && (e.stopPropagation(), e.preventDefault()), this.isVisible = !0, this.element.trigger({
						type : "show",
						date : this.date
					})
		},
		hide : function() {
			this.isVisible && (this.isInline || (this.picker.hide(), g(window).off("resize", this.place), this.viewMode = this.startViewMode, this.showMode(), this.isInput || g(document).off("mousedown", this.hide), this.forceParse && (this.isInput && this.element.val() || this.hasInput && this.element.find("input").val()) && this.setValue(), this.isVisible = !1, this.element.trigger({
						type : "hide",
						date : this.date
					})))
		},
		remove : function() {
			this._detachEvents(), this.picker.remove(), delete this.picker, delete this.element.data().datetimepicker
		},
		getDate : function() {
			var h = this.getUTCDate();
			return new Date(h.getTime() + 60000 * h.getTimezoneOffset())
		},
		getUTCDate : function() {
			return this.date
		},
		setDate : function(h) {
			this.setUTCDate(new Date(h.getTime() - 60000 * h.getTimezoneOffset()))
		},
		setUTCDate : function(h) {
			h >= this.startDate && h <= this.endDate ? (this.date = h, this.setValue(), this.viewDate = this.date, this.fill()) : this.element.trigger({
						type : "outOfRange",
						date : h,
						startDate : this.startDate,
						endDate : this.endDate
					})
		},
		setFormat : function(j) {
			this.format = b.parseFormat(j, this.formatType);
			var h;
			this.isInput ? h = this.element : this.component && (h = this.element.find("input")), h && h.val() && this.setValue()
		},
		setValue : function() {
			var e = this.getFormattedDate();
			this.isInput ? this.element.val(e) : (this.component && this.element.find("input").val(e), this.element.data("date", e)), this.linkField && g("#" + this.linkField).val(this.getFormattedDate(this.linkFormat))
		},
		getFormattedDate : function(h) {
			return void 0 == h && (h = this.format), b.formatDate(this.date, h, this.language, this.formatType)
		},
		setStartDate : function(h) {
			this.startDate = h || -1 / 0, this.startDate !== -1 / 0 && (this.startDate = b.parseDate(this.startDate, this.format, this.language, this.formatType)), this.update(), this.updateNavArrows()
		},
		setEndDate : function(h) {
			this.endDate = h || 1 / 0, 1 / 0 !== this.endDate && (this.endDate = b.parseDate(this.endDate, this.format, this.language, this.formatType)), this.update(), this.updateNavArrows()
		},
		setDaysOfWeekDisabled : function(e) {
			this.daysOfWeekDisabled = e || [], g.isArray(this.daysOfWeekDisabled) || (this.daysOfWeekDisabled = this.daysOfWeekDisabled.split(/,\s*/)), this.daysOfWeekDisabled = g.map(this.daysOfWeekDisabled, function(h) {
						return parseInt(h, 10)
					}), this.update(), this.updateNavArrows()
		},
		place : function() {
			if (!this.isInline) {
				var j = 0;
				g("div").each(function() {
							var m = parseInt(g(this).css("zIndex"), 10);
							m > j && (j = m)
						});
				var h, k, e, l = j + 10;
				this.component ? (h = this.component.offset(), e = h.left, ("bottom-left" == this.pickerPosition || "top-left" == this.pickerPosition) && (e += this.component.outerWidth() - this.picker.outerWidth())) : (h = this.element.offset(), e = h.left), k = "top-left" == this.pickerPosition || "top-right" == this.pickerPosition ? h.top - this.picker.outerHeight() : h.top + this.height, this.picker.css({
							top : k,
							left : e,
							zIndex : l
						})
			}
		},
		update : function() {
			var j, h = !1;
			arguments && arguments.length && ("string" == typeof arguments[0] || arguments[0] instanceof Date) ? (j = arguments[0], h = !0) : (j = this.element.data("date") || (this.isInput ? this.element.val() : this.element.find("input").val()) || this.initialDate, ("string" == typeof j || j instanceof String) && (j = j.replace(/^\s+|\s+$/g, ""))), j || (j = new Date, h = !1), this.date = b.parseDate(j, this.format, this.language, this.formatType), h && this.setValue(), this.viewDate = this.date < this.startDate ? new Date(this.startDate) : this.date > this.endDate ? new Date(this.endDate) : new Date(this.date), this.fill()
		},
		fillDow : function() {
			for (var j = this.weekStart, h = "<tr>"; j < this.weekStart + 7;) {
				h += '<th class="dow">' + f[this.language].daysMin[j++ % 7] + "</th>"
			}
			h += "</tr>", this.picker.find(".datetimepicker-days thead").append(h)
		},
		fillMonths : function() {
			for (var j = "", h = 0; 12 > h;) {
				j += '<span class="month">' + f[this.language].monthsShort[h++] + "</span>"
			}
			this.picker.find(".datetimepicker-months td").html(j)
		},
		fill : function() {
			if (null != this.date && null != this.viewDate) {
				var aa = new Date(this.viewDate), W = aa.getUTCFullYear(), ab = aa.getUTCMonth(), R = aa.getUTCDate(), P = aa.getUTCHours(), ae = aa.getUTCMinutes(), Y = this.startDate !== -1 / 0 ? this.startDate.getUTCFullYear() : -1 / 0, K = this.startDate !== -1 / 0 ? this.startDate.getUTCMonth() : -1 / 0, af = 1 / 0 !== this.endDate ? this.endDate.getUTCFullYear() : 1 / 0, X = 1 / 0 !== this.endDate ? this.endDate.getUTCMonth() : 1 / 0, Q = new d(this.date.getUTCFullYear(), this.date.getUTCMonth(), this.date.getUTCDate()).valueOf(), I = new Date;
				if (this.picker.find(".datetimepicker-days thead th:eq(1)").text(f[this.language].months[ab] + " " + W), "time" == this.formatViewType) {
					var ac = P % 12 ? P % 12 : 12, ad = (10 > ac ? "0" : "") + ac, G = (10 > ae ? "0" : "") + ae, L = f[this.language].meridiem[12 > P ? 0 : 1];
					this.picker.find(".datetimepicker-hours thead th:eq(1)").text(ad + ":" + G + " " + L.toUpperCase()), this.picker.find(".datetimepicker-minutes thead th:eq(1)").text(ad + ":" + G + " " + L.toUpperCase())
				} else {
					this.picker.find(".datetimepicker-hours thead th:eq(1)").text(R + " " + f[this.language].months[ab] + " " + W), this.picker.find(".datetimepicker-minutes thead th:eq(1)").text(R + " " + f[this.language].months[ab] + " " + W)
				}
				this.picker.find("tfoot th.today").text(f[this.language].today).toggle(this.todayBtn !== !1), this.updateNavArrows(), this.fillMonths();
				var A = d(W, ab - 1, 28, 0, 0, 0, 0), q = b.getDaysInMonth(A.getUTCFullYear(), A.getUTCMonth());
				A.setUTCDate(q), A.setUTCDate(q - (A.getUTCDay() - this.weekStart + 7) % 7);
				var z = new Date(A);
				z.setUTCDate(z.getUTCDate() + 42), z = z.valueOf();
				for (var O, Z = []; A.valueOf() < z;) {
					A.getUTCDay() == this.weekStart && Z.push("<tr>"), O = "", A.getUTCFullYear() < W || A.getUTCFullYear() == W && A.getUTCMonth() < ab ? O += " old" : (A.getUTCFullYear() > W || A.getUTCFullYear() == W && A.getUTCMonth() > ab) && (O += " new"), this.todayHighlight && A.getUTCFullYear() == I.getFullYear() && A.getUTCMonth() == I.getMonth() && A.getUTCDate() == I.getDate() && (O += " today"), A.valueOf() == Q && (O += " active"), (A.valueOf() + 86400000 <= this.startDate || A.valueOf() > this.endDate || -1 !== g.inArray(A.getUTCDay(), this.daysOfWeekDisabled)) && (O += " disabled"), Z.push('<td class="day' + O + '">' + A.getUTCDate() + "</td>"), A.getUTCDay() == this.weekEnd && Z.push("</tr>"), A.setUTCDate(A.getUTCDate() + 1)
				}
				this.picker.find(".datetimepicker-days tbody").empty().append(Z.join("")), Z = [];
				for (var j = "", ag = "", e = "", s = 0; 24 > s; s++) {
					var J = d(W, ab, R, s);
					O = "", J.valueOf() + 3600000 <= this.startDate || J.valueOf() > this.endDate ? O += " disabled" : P == s && (O += " active"), this.showMeridian && 2 == f[this.language].meridiem.length ? (ag = 12 > s ? f[this.language].meridiem[0] : f[this.language].meridiem[1], ag != e && ("" != e && Z.push("</fieldset>"), Z.push('<fieldset class="hour"><legend>' + ag.toUpperCase() + "</legend>")), e = ag, j = s % 12 ? s % 12 : 12, Z.push('<span class="hour' + O + " hour_" + (12 > s ? "am" : "pm") + '">' + j + "</span>"), 23 == s && Z.push("</fieldset>")) : (j = s + ":00", Z.push('<span class="hour' + O + '">' + j + "</span>"))
				}
				this.picker.find(".datetimepicker-hours td").html(Z.join("")), Z = [], j = "", ag = "", e = "";
				for (var s = 0; 60 > s; s += this.minuteStep) {
					var J = d(W, ab, R, P, s, 0);
					O = "", J.valueOf() < this.startDate || J.valueOf() > this.endDate ? O += " disabled" : Math.floor(ae / this.minuteStep) == Math.floor(s / this.minuteStep) && (O += " active"), this.showMeridian && 2 == f[this.language].meridiem.length ? (ag = 12 > P ? f[this.language].meridiem[0] : f[this.language].meridiem[1], ag != e && ("" != e && Z.push("</fieldset>"), Z.push('<fieldset class="minute"><legend>' + ag.toUpperCase() + "</legend>")), e = ag, j = P % 12 ? P % 12 : 12, Z.push('<span class="minute' + O + '">' + j + ":" + (10 > s ? "0" + s : s) + "</span>"), 59 == s && Z.push("</fieldset>")) : (j = s + ":00", Z.push('<span class="minute' + O + '">' + P + ":" + (10 > s ? "0" + s : s) + "</span>"))
				}
				this.picker.find(".datetimepicker-minutes td").html(Z.join(""));
				var B = this.date.getUTCFullYear(), E = this.picker.find(".datetimepicker-months").find("th:eq(1)").text(W).end().find("span").removeClass("active");
				B == W && E.eq(this.date.getUTCMonth()).addClass("active"), (Y > W || W > af) && E.addClass("disabled"), W == Y && E.slice(0, K).addClass("disabled"), W == af && E.slice(X + 1).addClass("disabled"), Z = "", W = 10 * parseInt(W / 10, 10);
				var t = this.picker.find(".datetimepicker-years").find("th:eq(1)").text(W + "-" + (W + 9)).end().find("td");
				W -= 1;
				for (var s = -1; 11 > s; s++) {
					Z += '<span class="year' + (-1 == s || 10 == s ? " old" : "") + (B == W ? " active" : "") + (Y > W || W > af ? " disabled" : "") + '">' + W + "</span>", W += 1
				}
				t.html(Z), this.place()
			}
		},
		updateNavArrows : function() {
			var m = new Date(this.viewDate), k = m.getUTCFullYear(), j = m.getUTCMonth(), l = m.getUTCDate(), h = m.getUTCHours();
			switch (this.viewMode) {
				case 0 :
					this.startDate !== -1 / 0 && k <= this.startDate.getUTCFullYear() && j <= this.startDate.getUTCMonth() && l <= this.startDate.getUTCDate() && h <= this.startDate.getUTCHours() ? this.picker.find(".prev").css({
								visibility : "hidden"
							}) : this.picker.find(".prev").css({
								visibility : "visible"
							}), 1 / 0 !== this.endDate && k >= this.endDate.getUTCFullYear() && j >= this.endDate.getUTCMonth() && l >= this.endDate.getUTCDate() && h >= this.endDate.getUTCHours() ? this.picker.find(".next").css({
								visibility : "hidden"
							}) : this.picker.find(".next").css({
								visibility : "visible"
							});
					break;
				case 1 :
					this.startDate !== -1 / 0 && k <= this.startDate.getUTCFullYear() && j <= this.startDate.getUTCMonth() && l <= this.startDate.getUTCDate() ? this.picker.find(".prev").css({
								visibility : "hidden"
							}) : this.picker.find(".prev").css({
								visibility : "visible"
							}), 1 / 0 !== this.endDate && k >= this.endDate.getUTCFullYear() && j >= this.endDate.getUTCMonth() && l >= this.endDate.getUTCDate() ? this.picker.find(".next").css({
								visibility : "hidden"
							}) : this.picker.find(".next").css({
								visibility : "visible"
							});
					break;
				case 2 :
					this.startDate !== -1 / 0 && k <= this.startDate.getUTCFullYear() && j <= this.startDate.getUTCMonth() ? this.picker.find(".prev").css({
								visibility : "hidden"
							}) : this.picker.find(".prev").css({
								visibility : "visible"
							}), 1 / 0 !== this.endDate && k >= this.endDate.getUTCFullYear() && j >= this.endDate.getUTCMonth() ? this.picker.find(".next").css({
								visibility : "hidden"
							}) : this.picker.find(".next").css({
								visibility : "visible"
							});
					break;
				case 3 :
				case 4 :
					this.startDate !== -1 / 0 && k <= this.startDate.getUTCFullYear() ? this.picker.find(".prev").css({
								visibility : "hidden"
							}) : this.picker.find(".prev").css({
								visibility : "visible"
							}), 1 / 0 !== this.endDate && k >= this.endDate.getUTCFullYear() ? this.picker.find(".next").css({
								visibility : "hidden"
							}) : this.picker.find(".next").css({
								visibility : "visible"
							})
			}
		},
		mousewheel : function(j) {
			if (j.preventDefault(), j.stopPropagation(), !this.wheelPause) {
				this.wheelPause = !0;
				var h = j.originalEvent, k = h.wheelDelta, e = k > 0 ? 1 : 0 === k ? 0 : -1;
				this.wheelViewModeNavigationInverseDirection && (e = -e), this.showMode(e), setTimeout(g.proxy(function() {
									this.wheelPause = !1
								}, this), this.wheelViewModeNavigationDelay)
			}
		},
		click : function(t) {
			t.stopPropagation(), t.preventDefault();
			var z = g(t.target).closest("span, td, th, legend");
			if (1 == z.length) {
				if (z.is(".disabled")) {
					return this.element.trigger({
								type : "outOfRange",
								date : this.viewDate,
								startDate : this.startDate,
								endDate : this.endDate
							}), void 0
				}
				switch (z[0].nodeName.toLowerCase()) {
					case "th" :
						switch (z[0].className) {
							case "switch" :
								this.showMode(1);
								break;
							case "prev" :
							case "next" :
								var k = b.modes[this.viewMode].navStep * ("prev" == z[0].className ? -1 : 1);
								switch (this.viewMode) {
									case 0 :
										this.viewDate = this.moveHour(this.viewDate, k);
										break;
									case 1 :
										this.viewDate = this.moveDate(this.viewDate, k);
										break;
									case 2 :
										this.viewDate = this.moveMonth(this.viewDate, k);
										break;
									case 3 :
									case 4 :
										this.viewDate = this.moveYear(this.viewDate, k)
								}
								this.fill();
								break;
							case "today" :
								var v = new Date;
								v = d(v.getFullYear(), v.getMonth(), v.getDate(), v.getHours(), v.getMinutes(), v.getSeconds(), 0), v < this.startDate ? v = this.startDate : v > this.endDate && (v = this.endDate), this.viewMode = this.startViewMode, this.showMode(0), this._setDate(v), this.fill(), this.autoclose && this.hide()
						}
						break;
					case "span" :
						if (!z.is(".disabled")) {
							var j = this.viewDate.getUTCFullYear(), e = this.viewDate.getUTCMonth(), w = this.viewDate.getUTCDate(), q = this.viewDate.getUTCHours(), y = this.viewDate.getUTCMinutes(), x = this.viewDate.getUTCSeconds();
							if (z.is(".month") ? (this.viewDate.setUTCDate(1), e = z.parent().find("span").index(z), w = this.viewDate.getUTCDate(), this.viewDate.setUTCMonth(e), this.element.trigger({
										type : "changeMonth",
										date : this.viewDate
									}), this.viewSelect >= 3 && this._setDate(d(j, e, w, q, y, x, 0))) : z.is(".year") ? (this.viewDate.setUTCDate(1), j = parseInt(z.text(), 10) || 0, this.viewDate.setUTCFullYear(j), this.element.trigger({
										type : "changeYear",
										date : this.viewDate
									}), this.viewSelect >= 4 && this._setDate(d(j, e, w, q, y, x, 0))) : z.is(".hour") ? (q = parseInt(z.text(), 10) || 0, (z.hasClass("hour_am") || z.hasClass("hour_pm")) && (12 == q && z.hasClass("hour_am") ? q = 0 : 12 != q && z.hasClass("hour_pm") && (q += 12)), this.viewDate.setUTCHours(q), this.element.trigger({
										type : "changeHour",
										date : this.viewDate
									}), this.viewSelect >= 1 && this._setDate(d(j, e, w, q, y, x, 0))) : z.is(".minute") && (y = parseInt(z.text().substr(z.text().indexOf(":") + 1), 10) || 0, this.viewDate.setUTCMinutes(y), this.element.trigger({
										type : "changeMinute",
										date : this.viewDate
									}), this.viewSelect >= 0 && this._setDate(d(j, e, w, q, y, x, 0))), 0 != this.viewMode) {
								var p = this.viewMode;
								this.showMode(-1), this.fill(), p == this.viewMode && this.autoclose && this.hide()
							} else {
								this.fill(), this.autoclose && this.hide()
							}
						}
						break;
					case "td" :
						if (z.is(".day") && !z.is(".disabled")) {
							var w = parseInt(z.text(), 10) || 1, j = this.viewDate.getUTCFullYear(), e = this.viewDate.getUTCMonth(), q = this.viewDate.getUTCHours(), y = this.viewDate.getUTCMinutes(), x = this.viewDate.getUTCSeconds();
							z.is(".old") ? 0 === e ? (e = 11, j -= 1) : e -= 1 : z.is(".new") && (11 == e ? (e = 0, j += 1) : e += 1), this.viewDate.setUTCFullYear(j), this.viewDate.setUTCMonth(e, w), this.element.trigger({
										type : "changeDay",
										date : this.viewDate
									}), this.viewSelect >= 2 && this._setDate(d(j, e, w, q, y, x, 0))
						}
						var p = this.viewMode;
						this.showMode(-1), this.fill(), p == this.viewMode && this.autoclose && this.hide()
				}
			}
		},
		_setDate : function(k, j) {
			j && "date" != j || (this.date = k), j && "view" != j || (this.viewDate = k), this.fill(), this.setValue();
			var h;
			this.isInput ? h = this.element : this.component && (h = this.element.find("input")), h && (h.change(), this.autoclose && (!j || "date" == j)), this.element.trigger({
						type : "changeDate",
						date : this.date
					})
		},
		moveMinute : function(k, j) {
			if (!j) {
				return k
			}
			var h = new Date(k.valueOf());
			return h.setUTCMinutes(h.getUTCMinutes() + j * this.minuteStep), h
		},
		moveHour : function(k, j) {
			if (!j) {
				return k
			}
			var h = new Date(k.valueOf());
			return h.setUTCHours(h.getUTCHours() + j), h
		},
		moveDate : function(k, j) {
			if (!j) {
				return k
			}
			var h = new Date(k.valueOf());
			return h.setUTCDate(h.getUTCDate() + j), h
		},
		moveMonth : function(q, v) {
			if (!v) {
				return q
			}
			var m, w, u = new Date(q.valueOf()), l = u.getUTCDate(), p = u.getUTCMonth(), k = Math.abs(v);
			if (v = v > 0 ? 1 : -1, 1 == k) {
				w = -1 == v ? function() {
					return u.getUTCMonth() == p
				} : function() {
					return u.getUTCMonth() != m
				}, m = p + v, u.setUTCMonth(m), (0 > m || m > 11) && (m = (m + 12) % 12)
			} else {
				for (var j = 0; k > j; j++) {
					u = this.moveMonth(u, v)
				}
				m = u.getUTCMonth(), u.setUTCDate(l), w = function() {
					return m != u.getUTCMonth()
				}
			}
			for (; w();) {
				u.setUTCDate(--l), u.setUTCMonth(m)
			}
			return u
		},
		moveYear : function(j, h) {
			return this.moveMonth(j, 12 * h)
		},
		dateWithinRange : function(h) {
			return h >= this.startDate && h <= this.endDate
		},
		keydown : function(p) {
			if (this.picker.is(":not(:visible)")) {
				return 27 == p.keyCode && this.show(), void 0
			}
			var l, k, o, j = !1;
			switch (p.keyCode) {
				case 27 :
					this.hide(), p.preventDefault();
					break;
				case 37 :
				case 39 :
					if (!this.keyboardNavigation) {
						break
					}
					l = 37 == p.keyCode ? -1 : 1, viewMode = this.viewMode, p.ctrlKey ? viewMode += 2 : p.shiftKey && (viewMode += 1), 4 == viewMode ? (k = this.moveYear(this.date, l), o = this.moveYear(this.viewDate, l)) : 3 == viewMode ? (k = this.moveMonth(this.date, l), o = this.moveMonth(this.viewDate, l)) : 2 == viewMode ? (k = this.moveDate(this.date, l), o = this.moveDate(this.viewDate, l)) : 1 == viewMode ? (k = this.moveHour(this.date, l), o = this.moveHour(this.viewDate, l)) : 0 == viewMode && (k = this.moveMinute(this.date, l), o = this.moveMinute(this.viewDate, l)), this.dateWithinRange(k) && (this.date = k, this.viewDate = o, this.setValue(), this.update(), p.preventDefault(), j = !0);
					break;
				case 38 :
				case 40 :
					if (!this.keyboardNavigation) {
						break
					}
					l = 38 == p.keyCode ? -1 : 1, viewMode = this.viewMode, p.ctrlKey ? viewMode += 2 : p.shiftKey && (viewMode += 1), 4 == viewMode ? (k = this.moveYear(this.date, l), o = this.moveYear(this.viewDate, l)) : 3 == viewMode ? (k = this.moveMonth(this.date, l), o = this.moveMonth(this.viewDate, l)) : 2 == viewMode ? (k = this.moveDate(this.date, 7 * l), o = this.moveDate(this.viewDate, 7 * l)) : 1 == viewMode ? this.showMeridian ? (k = this.moveHour(this.date, 6 * l), o = this.moveHour(this.viewDate, 6 * l)) : (k = this.moveHour(this.date, 4 * l), o = this.moveHour(this.viewDate, 4 * l)) : 0 == viewMode && (k = this.moveMinute(this.date, 4 * l), o = this.moveMinute(this.viewDate, 4 * l)), this.dateWithinRange(k)
							&& (this.date = k, this.viewDate = o, this.setValue(), this.update(), p.preventDefault(), j = !0);
					break;
				case 13 :
					if (0 != this.viewMode) {
						var q = this.viewMode;
						this.showMode(-1), this.fill(), q == this.viewMode && this.autoclose && this.hide()
					} else {
						this.fill(), this.autoclose && this.hide()
					}
					p.preventDefault();
					break;
				case 9 :
					this.hide()
			}
			if (j) {
				var m;
				this.isInput ? m = this.element : this.component && (m = this.element.find("input")), m && m.change(), this.element.trigger({
							type : "changeDate",
							date : this.date
						})
			}
		},
		showMode : function(j) {
			if (j) {
				var h = Math.max(0, Math.min(b.modes.length - 1, this.viewMode + j));
				h >= this.minView && h <= this.maxView && (this.element.trigger({
							type : "changeMode",
							date : this.viewDate,
							oldViewMode : this.viewMode,
							newViewMode : h
						}), this.viewMode = h)
			}
			this.picker.find(">div").hide().filter(".datetimepicker-" + b.modes[this.viewMode].clsName).css("display", "block"), this.updateNavArrows()
		},
		reset : function() {
			this._setDate(null, "date")
		}
	}, g.fn.datetimepicker = function(e) {
		var h = Array.apply(null, arguments);
		return h.shift(), this.each(function() {
					var j = g(this), l = j.data("datetimepicker"), k = "object" == typeof e && e;
					l || j.data("datetimepicker", l = new c(this, g.extend({}, g.fn.datetimepicker.defaults, k))), "string" == typeof e && "function" == typeof l[e] && l[e].apply(l, h)
				})
	}, g.fn.datetimepicker.defaults = {}, g.fn.datetimepicker.Constructor = c;
	var f = g.fn.datetimepicker.dates = {
		en : {
			days : ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
			daysShort : ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
			daysMin : ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
			months : ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
			monthsShort : ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
			meridiem : ["am", "pm"],
			suffix : ["st", "nd", "rd", "th"],
			today : "Today"
		}
	};
	f["zh-cn"] = {
		days : ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort : ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin : ["日", "一", "二", "三", "四", "五", "六", "日"],
		months : ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort : ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today : "今日",
		suffix : [],
		meridiem : []
	}, f["zh-tw"] = {
		days : ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort : ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin : ["日", "一", "二", "三", "四", "五", "六", "日"],
		months : ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort : ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today : "今天",
		suffix : [],
		meridiem : ["上午", "下午"]
	};
	var b = {
		modes : [{
					clsName : "minutes",
					navFnc : "Hours",
					navStep : 1
				}, {
					clsName : "hours",
					navFnc : "Date",
					navStep : 1
				}, {
					clsName : "days",
					navFnc : "Month",
					navStep : 1
				}, {
					clsName : "months",
					navFnc : "FullYear",
					navStep : 1
				}, {
					clsName : "years",
					navFnc : "FullYear",
					navStep : 10
				}],
		isLeapYear : function(h) {
			return 0 === h % 4 && 0 !== h % 100 || 0 === h % 400
		},
		getDaysInMonth : function(j, h) {
			return [31, b.isLeapYear(j) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][h]
		},
		getDefaultFormat : function(j, h) {
			if ("standard" == j) {
				return "input" == h ? "yyyy-mm-dd hh:ii" : "yyyy-mm-dd hh:ii:ss"
			}
			if ("php" == j) {
				return "input" == h ? "Y-m-d H:i" : "Y-m-d H:i:s"
			}
			throw new Error("Invalid format type.")
		},
		validParts : function(h) {
			if ("standard" == h) {
				return /hh?|HH?|p|P|ii?|ss?|dd?|DD?|mm?|MM?|yy(?:yy)?/g
			}
			if ("php" == h) {
				return /[dDjlNwzFmMnStyYaABgGhHis]/g
			}
			throw new Error("Invalid format type.")
		},
		nonpunctuation : /[^ -\/:-@\[-`{-~\t\n\rTZ]+/g,
		parseFormat : function(l, j) {
			var h = l.replace(this.validParts(j), "\0").split("\0"), k = l.match(this.validParts(j));
			if (!h || !h.length || !k || 0 == k.length) {
				throw new Error("Invalid date format.")
			}
			return {
				separators : h,
				parts : k
			}
		},
		parseDate : function(G, s, z, q) {
			if (G instanceof Date) {
				var j = new Date(G.valueOf() - 60000 * G.getTimezoneOffset());
				return j.setMilliseconds(0), j
			}
			if (/^\d{4}\-\d{1,2}\-\d{1,2}$/.test(G) && (s = this.parseFormat("yyyy-mm-dd", q)), /^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}$/.test(G) && (s = this.parseFormat("yyyy-mm-dd hh:ii", q)), /^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}\:\d{1,2}[Z]{0,1}$/.test(G) && (s = this.parseFormat("yyyy-mm-dd hh:ii:ss", q)), /^[-+]\d+[dmwy]([\s,]+[-+]\d+[dmwy])*$/.test(G)) {
				var C, x, J = /([-+]\d+)([dmwy])/, E = G.match(/([-+]\d+)([dmwy])/g);
				G = new Date;
				for (var t = 0; t < E.length; t++) {
					switch (C = J.exec(E[t]), x = parseInt(C[1]), C[2]) {
						case "d" :
							G.setUTCDate(G.getUTCDate() + x);
							break;
						case "m" :
							G = c.prototype.moveMonth.call(c.prototype, G, x);
							break;
						case "w" :
							G.setUTCDate(G.getUTCDate() + 7 * x);
							break;
						case "y" :
							G = c.prototype.moveYear.call(c.prototype, G, x)
					}
				}
				return d(G.getUTCFullYear(), G.getUTCMonth(), G.getUTCDate(), G.getUTCHours(), G.getUTCMinutes(), G.getUTCSeconds(), 0)
			}
			var k, I, C, E = G && G.match(this.nonpunctuation) || [], G = new Date(0, 0, 0, 0, 0, 0, 0), A = {}, B = ["hh", "h", "ii", "i", "ss", "s", "yyyy", "yy", "M", "MM", "m", "mm", "D", "DD", "d", "dd", "H", "HH", "p", "P"], H = {
				hh : function(l, h) {
					return l.setUTCHours(h)
				},
				h : function(l, h) {
					return l.setUTCHours(h)
				},
				HH : function(l, h) {
					return l.setUTCHours(12 == h ? 0 : h)
				},
				H : function(l, h) {
					return l.setUTCHours(12 == h ? 0 : h)
				},
				ii : function(l, h) {
					return l.setUTCMinutes(h)
				},
				i : function(l, h) {
					return l.setUTCMinutes(h)
				},
				ss : function(l, h) {
					return l.setUTCSeconds(h)
				},
				s : function(l, h) {
					return l.setUTCSeconds(h)
				},
				yyyy : function(l, h) {
					return l.setUTCFullYear(h)
				},
				yy : function(l, h) {
					return l.setUTCFullYear(2000 + h)
				},
				m : function(l, h) {
					for (h -= 1; 0 > h;) {
						h += 12
					}
					for (h %= 12, l.setUTCMonth(h); l.getUTCMonth() != h;) {
						l.setUTCDate(l.getUTCDate() - 1)
					}
					return l
				},
				d : function(l, h) {
					return l.setUTCDate(h)
				},
				p : function(l, h) {
					return l.setUTCHours(1 == h ? l.getUTCHours() + 12 : l.getUTCHours())
				}
			};
			if (H.M = H.MM = H.mm = H.m, H.dd = H.d, H.P = H.p, G = d(G.getFullYear(), G.getMonth(), G.getDate(), G.getHours(), G.getMinutes(), G.getSeconds()), E.length == s.parts.length) {
				for (var t = 0, e = s.parts.length; e > t; t++) {
					if (k = parseInt(E[t], 10), C = s.parts[t], isNaN(k)) {
						switch (C) {
							case "MM" :
								I = g(f[z].months).filter(function() {
											var l = this.slice(0, E[t].length), h = E[t].slice(0, l.length);
											return l == h
										}), k = g.inArray(I[0], f[z].months) + 1;
								break;
							case "M" :
								I = g(f[z].monthsShort).filter(function() {
											var l = this.slice(0, E[t].length), h = E[t].slice(0, l.length);
											return l == h
										}), k = g.inArray(I[0], f[z].monthsShort) + 1;
								break;
							case "p" :
							case "P" :
								k = g.inArray(E[t].toLowerCase(), f[z].meridiem)
						}
					}
					A[C] = k
				}
				for (var F, t = 0; t < B.length; t++) {
					F = B[t], F in A && !isNaN(A[F]) && H[F](G, A[F])
				}
			}
			return G
		},
		formatDate : function(k, j, u, m) {
			if (null == k) {
				return ""
			}
			var s;
			if ("standard" == m) {
				s = {
					yy : k.getUTCFullYear().toString().substring(2),
					yyyy : k.getUTCFullYear(),
					m : k.getUTCMonth() + 1,
					M : f[u].monthsShort[k.getUTCMonth()],
					MM : f[u].months[k.getUTCMonth()],
					d : k.getUTCDate(),
					D : f[u].daysShort[k.getUTCDay()],
					DD : f[u].days[k.getUTCDay()],
					p : 2 == f[u].meridiem.length ? f[u].meridiem[k.getUTCHours() < 12 ? 0 : 1] : "",
					h : k.getUTCHours(),
					i : k.getUTCMinutes(),
					s : k.getUTCSeconds()
				}, s.H = 2 == f[u].meridiem.length ? 0 == s.h % 12 ? 12 : s.h % 12 : s.h, s.HH = (s.H < 10 ? "0" : "") + s.H, s.P = s.p.toUpperCase(), s.hh = (s.h < 10 ? "0" : "") + s.h, s.ii = (s.i < 10 ? "0" : "") + s.i, s.ss = (s.s < 10 ? "0" : "") + s.s, s.dd = (s.d < 10 ? "0" : "") + s.d, s.mm = (s.m < 10 ? "0" : "") + s.m
			} else {
				if ("php" != m) {
					throw new Error("Invalid format type.")
				}
				s = {
					y : k.getUTCFullYear().toString().substring(2),
					Y : k.getUTCFullYear(),
					F : f[u].months[k.getUTCMonth()],
					M : f[u].monthsShort[k.getUTCMonth()],
					n : k.getUTCMonth() + 1,
					t : b.getDaysInMonth(k.getUTCFullYear(), k.getUTCMonth()),
					j : k.getUTCDate(),
					l : f[u].days[k.getUTCDay()],
					D : f[u].daysShort[k.getUTCDay()],
					w : k.getUTCDay(),
					N : 0 == k.getUTCDay() ? 7 : k.getUTCDay(),
					S : k.getUTCDate() % 10 <= f[u].suffix.length ? f[u].suffix[k.getUTCDate() % 10 - 1] : "",
					a : 2 == f[u].meridiem.length ? f[u].meridiem[k.getUTCHours() < 12 ? 0 : 1] : "",
					g : 0 == k.getUTCHours() % 12 ? 12 : k.getUTCHours() % 12,
					G : k.getUTCHours(),
					i : k.getUTCMinutes(),
					s : k.getUTCSeconds()
				}, s.m = (s.n < 10 ? "0" : "") + s.n, s.d = (s.j < 10 ? "0" : "") + s.j, s.A = s.a.toString().toUpperCase(), s.h = (s.g < 10 ? "0" : "") + s.g, s.H = (s.G < 10 ? "0" : "") + s.G, s.i = (s.i < 10 ? "0" : "") + s.i, s.s = (s.s < 10 ? "0" : "") + s.s
			}
			for (var k = [], p = g.extend([], j.separators), q = 0, e = j.parts.length; e > q; q++) {
				p.length && k.push(p.shift()), k.push(s[j.parts[q]])
			}
			return p.length && k.push(p.shift()), k.join("")
		},
		convertViewMode : function(h) {
			switch (h) {
				case 4 :
				case "decade" :
					h = 4;
					break;
				case 3 :
				case "year" :
					h = 3;
					break;
				case 2 :
				case "month" :
					h = 2;
					break;
				case 1 :
				case "day" :
					h = 1;
					break;
				case 0 :
				case "hour" :
					h = 0
			}
			return h
		},
		headTemplate : '<thead><tr><th class="prev"><i class="icon-arrow-left"/></th><th colspan="5" class="switch"></th><th class="next"><i class="icon-arrow-right"/></th></tr></thead>',
		contTemplate : '<tbody><tr><td colspan="7"></td></tr></tbody>',
		footTemplate : '<tfoot><tr><th colspan="7" class="today"></th></tr></tfoot>'
	};
	b.template = '<div class="datetimepicker"><div class="datetimepicker-minutes"><table class=" table-condensed">' + b.headTemplate + b.contTemplate + b.footTemplate + '</table></div><div class="datetimepicker-hours"><table class=" table-condensed">' + b.headTemplate + b.contTemplate + b.footTemplate + '</table></div><div class="datetimepicker-days"><table class=" table-condensed">' + b.headTemplate + "<tbody></tbody>" + b.footTemplate + '</table></div><div class="datetimepicker-months"><table class="table-condensed">' + b.headTemplate + b.contTemplate + b.footTemplate + '</table></div><div class="datetimepicker-years"><table class="table-condensed">' + b.headTemplate + b.contTemplate + b.footTemplate + "</table></div></div>", g.fn.datetimepicker.DPGlobal = b, g.fn.datetimepicker.noConflict = function() {
		return g.fn.datetimepicker = old, this
	}, g(document).on("focus.datetimepicker.data-api click.datetimepicker.data-api", '[data-provide="datetimepicker"]', function(h) {
				var e = g(this);
				e.data("datetimepicker") || (h.preventDefault(), e.datetimepicker("show"))
			}), g(function() {
				g('[data-provide="datetimepicker-inline"]').datetimepicker()
			})
}(window.jQuery);