package poo.ricardo.pdv_ui.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoConnect implements AcessoBanco {

	Connection conexao_banco;
	
	public BancoConnect() {
		this("root","");
	}
	public BancoConnect(String user,String pass){
		String url="jdbc:mysql://localhost:3306/projetoerp?user="+user+"&password="+pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao_banco=DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Mysql Não Encontrado");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public List<Produto> getListaProdutos() {
		ArrayList<Produto> output= new ArrayList<Produto>();
		String sql=
				"SELECT pr.idCadProduto AS id,"
				+ "fa.razaoSocial AS fabricante,"
				+ "ma.marca AS marca,"
				+ "mo.modelo AS modelo,"
				+ "ca.categoria AS categoria "
				+ "FROM cadProdutos AS pr "
				+ "INNER JOIN cadProdutoFabricantes AS fa "
				+ "ON pr.idCadProdutoFabricante = fa.idCadProdutoFabricante "
				+ "INNER JOIN cadProdutoMarcas AS ma "
				+ "ON pr.idCadProdutoMarca = ma.idCadProdutoMarca "
				+ "INNER JOIN cadProdutoModelos AS mo "
				+ "ON pr.idCadProdutoModelo = mo.idCadProdutoModelo "
				+ "INNER JOIN cadProdutoCategorias AS ca "
				+ "ON pr.idCadProdutoCategoria = ca.idCadProdutoCategoria "
				+ "WHERE pr.ativo = 'S'";
		try {
			Statement st = conexao_banco.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id=rs.getInt("id");
				output.add(
						new Produto(
								id,
								rs.getString("fabricante"),
								rs.getString("marca"),
								rs.getString("modelo"),
								rs.getString("categoria"),
								getPreco(id)
							)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return output;
	}

	private Double getPreco(int idProduto) {
		Double result=null;
		String sql="SELECT valor FROM cadProdutoPrecoVenda WHERE idCadProduto = "+idProduto+" ORDER BY dataPreco DESC LIMIT 1";
		try {
			Statement st = conexao_banco.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			result=rs.getDouble("valor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void novaVenda(String cpfcnpj, List<ProdVenda> p) {
		double t=0;
		int idCaixaMovimento=0;
		int idVenda=0;
		for(ProdVenda pr:p) {
			t+=pr.getTotal();
		}
		String sql="INSERT INTO ctrlCaixaMovimento (dataHora,tipoMovimento,operacao,valor) VALUES (NOW(),2,1,"+t+")";
		try {
			Statement st = conexao_banco.createStatement();
			st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			idCaixaMovimento=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="INSERT INTO ctrlCaixaVenda (idCaixaMovimento,cpfCnpjCliente,notaFiscal) VALUES ("+idCaixaMovimento+",'"+cpfcnpj+"',0)";
		try {
			Statement st = conexao_banco.createStatement();
			st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			idVenda=rs.getInt(1);
			for(ProdVenda pr:p) {
				insertProduto(idVenda,pr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void insertProduto(int idVenda,ProdVenda p) {
		String sql="INSERT INTO ctrlCaixaVendaItem (idVenda,idProduto,quantidade,preco) VALUES ("+idVenda+","+p.getCodigo()+","+p.getAmt()+","+p.getTotal()+")";
		try {
			Statement st = conexao_banco.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
