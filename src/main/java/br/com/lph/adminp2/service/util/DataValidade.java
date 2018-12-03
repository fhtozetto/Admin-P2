package br.com.lph.adminp2.service.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DataValidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date data;
	private Integer dias;
	
	public DataValidade() {
		super();
	}

	public DataValidade(Date data, Integer dias) {
		super();
		this.data = data;
		this.dias = dias;
	}
	
	public Date calcula(Date data, Integer dias) {
		Calendar vencimento = Calendar.getInstance();
		vencimento.setTime(data);
		vencimento.add(Calendar.DATE, + dias); // adiciona os dia de vencimento na data do sistema
		
		return vencimento.getTime();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

}
