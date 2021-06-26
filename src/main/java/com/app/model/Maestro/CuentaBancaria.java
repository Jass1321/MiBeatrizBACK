package com.app.model.Maestro;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cuenta_bancarias")
public class CuentaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(unique = true)
	private String num;
	@Column(unique = true)
	private String cci;
	private String moneda;
	
	//N Cuenta Bancaria -> 1 Banco
	@ManyToOne
	@JoinColumn(name = "banco_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Banco banco;
	
	/* CONSTRUCTOR*/
	public CuentaBancaria() {
		
	}

	public CuentaBancaria(@NotNull String num, String cci, String moneda, Banco banco) {
		super();
		this.num = num;
		this.cci = cci;
		this.moneda = moneda;
		this.banco = banco;
	}

	/* GET & SET*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
