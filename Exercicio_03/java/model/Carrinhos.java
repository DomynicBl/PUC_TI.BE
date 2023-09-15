package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Carrinhos implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DESCRICAO_DADRAO = "Novo Produto";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String descricao;
	private float preco;
	private int quantidade;
	private LocalDateTime data_Fabri;
	private LocalDate data_Valid;
	
	public Carrinhos() {
		id = -1;
		descricao = DESCRICAO_DADRAO;
		preco = 0.01F;
		quantidade = 0;
		data_Fabri = LocalDateTime.now();
		data_Valid = LocalDate.now().plusMonths(6);	
	}
	
	public Carrinhos(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao, LocalDate validade) {
		setId (id);
		setDescricao (descricao);
		setPreco (preco);
		setQuantidade (quantidade);
		setData_Fabri (fabricacao);
		setData_Valid(validade.atStartOfDay());
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	
//--------------------------------------------------------------------------------
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
	    if (descricao.length() >= 3)
	        this.descricao = descricao;
	}
	
//--------------------------------------------------------------------------------
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
	    if (preco > 0)
	        this.preco = preco;
	}
	
//--------------------------------------------------------------------------------	
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
	    if(quantidade >= 0 && quantidade <= MAX_ESTOQUE)
	        this.quantidade = quantidade;
	}
	
//--------------------------------------------------------------------------------
	
	public LocalDateTime getData_Fabri() {
		return data_Fabri;
	}
	
	public void setData_Fabri(LocalDateTime data_Fabri) {
		LocalDateTime agora = LocalDateTime.now();
		if(agora.compareTo(data_Fabri) >= 0)
			this.data_Fabri = data_Fabri;
	}
	
//--------------------------------------------------------------------------------
	
	public LocalDate getData_Valid() {
		return data_Valid;
	}
	
	public void setData_Valid(LocalDateTime data_Valid) {
	    if (getData_Fabri().isBefore(data_Valid.toLocalDate().atStartOfDay()))
	        this.data_Valid = data_Valid.toLocalDate();
	}

//--------------------------------------------------------------------------------
	
	public boolean emValidade() {
		return LocalDateTime.now().isBefore(this.getData_Valid().atTime(23, 59));
	}
	
	
	@Override
	public String toString() {
		return  "Produto: "+ descricao + 
				"Preço: R$	" + preco + 
				"Quantidade: " + quantidade + 
				"Fabricação: " + data_Fabri +
				"Validade:" + data_Valid;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Carrinhos) obj).getId());
	}
}
