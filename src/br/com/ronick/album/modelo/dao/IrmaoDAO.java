package br.com.ronick.album.modelo.dao;

import br.com.ronick.album.modelo.bd.AlbumBD;
import br.com.ronick.album.modelo.entidade.Irmao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IrmaoDAO {

    private static final String INSERIR_SQL = "Insert into irmao "
            + "(nome,contato) "
            + "values ('%s','%s')";
    private static final String ALTERAR_SQL = "Update irmao "
            + "set nome='%s', contato='%s' where id=%d";
    private static final String REMOVER_SQL = "delete from irmao "
            + "where id=%d";
    private static final String SELECIONAR_SQL = "Select * from irmao";
    private static final String SELECIONAR_POR_NOME = "Select * from irmao "
            + "where nome ='%s'";

    public static void inserir(Irmao irmao) {
        String sql = String.format(INSERIR_SQL,
                irmao.getNome(), irmao.getContato());
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Irmao irmao) {
        String sql = String.format(ALTERAR_SQL,
                irmao.getNome(), irmao.getContato(),
                irmao.getId());
        AlbumBD.execute(sql, true);
    }

    public static void remover(int id) {
        String sql = String.format(REMOVER_SQL, id);
        AlbumBD.execute(sql, true);
    }

    public static List<Irmao> selecionar(String sql) {
        List<Irmao> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                byte id = rs.getByte("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                lista.add(new Irmao(id, nome, contato));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Irmao> selecionarTodos() {
        return selecionar(String.format(SELECIONAR_SQL));
    }

    public static List<Irmao> selecionarPorNome(String nome) {
        return selecionar(String.format(SELECIONAR_POR_NOME, nome));
    }
}
