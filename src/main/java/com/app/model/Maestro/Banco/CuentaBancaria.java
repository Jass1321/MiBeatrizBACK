package com.app.model.Maestro.Banco;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CuentaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int num;
	private int cci;
	private String moneda;
	
	//N Cuenta Bancaria -> 1 Banco
	@ManyToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
	
	public CuentaBancaria() {
		
	}

	public CuentaBancaria(int num, int cci, String moneda, Banco banco) {
		super();
		this.num = num;
		this.cci = cci;
		this.moneda = moneda;
		this.banco = banco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCci() {
		return cci;
	}

	public void setCci(int cci) {
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
	
	

}
