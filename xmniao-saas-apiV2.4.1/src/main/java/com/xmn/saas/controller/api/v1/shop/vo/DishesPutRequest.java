package com.xmn.saas.controller.api.v1.shop.vo;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xmn.saas.base.Request;

public class DishesPutRequest extends Request {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "菜品名称必填")
	private String name;

	@NotNull(message = "菜品价格必填")
	@DecimalMin(value = "0.01", message = "菜品价格必须大于0.01")
	private BigDecimal price;

	@NotNull(message = "菜品图片必填")
	private String image;

	@Valid
	private Cake cake = new Cake();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Cake getCake() {
		return cake;
	}

	public void setCake(Cake cake) {
		this.cake = cake;
	}

	public class Cake {
		private Integer type;
		private Integer measure;
		
		@Valid
		private Color color = new Color();

		@NotNull(message = "蛋糕类型必填")
		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getMeasure() {
			return measure;
		}

		public void setMeasure(Integer measure) {
			this.measure = measure;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}
		
	}
	
	public class Color {
		@NotEmpty(message="Color k can't null.")
		private String k;
		private String v;

		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}

		public String getV() {
			return v;
		}

		public void setV(String v) {
			this.v = v;
		}

	}

}
