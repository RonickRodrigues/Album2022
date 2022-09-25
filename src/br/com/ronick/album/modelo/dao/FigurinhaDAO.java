package br.com.ronick.album.modelo.dao;

import br.com.ronick.album.modelo.bd.AlbumBD;
import br.com.ronick.album.modelo.entidade.Figurinha;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FigurinhaDAO {

    private static final String INSERIR_SQL = "Insert into figurinha "
            + "(numero,pagina,descricao,qtde) "
            + "values (%d,%d,'%s',%d)";
    private static final String ALTERAR_SQL = "Update figurinha "
            + "set descricao='%s', qtde=%d where numero=%d";
    private static final String SELECIONAR_SQL = "Select * from figurinha";
    private static final String SELECIONAR_PAGINA_SQL = "Select * "
            + "from figurinha where pagina=%d";
    private static final String SELECIONAR_ID = "Select * from figurinha "
            + "where numero =%d";

    public static void inserir(Figurinha figurinha) {
        String sql = String.format(ALTERAR_SQL,
                figurinha.getDescricao(),
                figurinha.getQuantidade(),
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    public static void inserir(int numero) {
        String sql = String.format(INSERIR_SQL,
                numero, (int) Math.ceil(numero / 10) + 1,
                "Indefinida", 0);
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Figurinha figurinha) {
        String sql = String.format(ALTERAR_SQL,
                figurinha.getDescricao(),
                figurinha.getQuantidade(),
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    private static List<Figurinha> selecionar(String sql) {
        List<Figurinha> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int numero = rs.getInt("numero");
                byte pagina = rs.getByte("pagina");
                String descricao = rs.getString("descricao");
                byte quantidade = rs.getByte("qtde");
                lista.add(new Figurinha(numero, pagina,
                        descricao, quantidade));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Figurinha> selecionarTodos() {
        return selecionar(SELECIONAR_SQL);
    }

    public static List<Figurinha> selecionarPorPagina(int pagina) {
        return selecionar(String.format(
                SELECIONAR_PAGINA_SQL, pagina));
    }

    public static List<Figurinha> selecionarPorId(int id) {
        return selecionar(String.format(SELECIONAR_ID, id));
    }
}