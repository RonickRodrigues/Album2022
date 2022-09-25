package br.com.ronick.album.modelo.bd;

import br.com.ronick.album.modelo.dao.FigurinhaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
create table figurinha 
    -> (numero int not null primary key,
    ->  pagina int not null,
    ->  descricao varchar(30) not null,
    ->  qtde int not null);
create table irmao
    -> (id int not null auto_increment primary key,
    ->  nome varchar(20) not null,
    ->  contato varchar(15) not null);
*/

public class AlbumBD {

    public static void popular() {
        int qtdeFigurinhas = FigurinhaDAO.selecionarTodos().size();
        if (qtdeFigurinhas < 300) {
            for (int i = 1; i <= 300; i++) {
                FigurinhaDAO.inserir(i);
            }
        }
    }

    public static void execute(String sql, boolean continuaNoErro) {
        // Fazer versão para conexões seguidas não desconectar
        Connection con = conectar();
        try {
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            if (!continuaNoErro) {
                System.exit(1);
            }
        }
        desconectar(con);
    }

    public static Connection conectar() {
        Connection con = null;
        final String USUARIO = "root";
        final String SENHA = "";
        final String URL = "jdbc:mysql://localhost:3306/albumDaCopa2022";
        try {
            con = DriverManager.getConnection(URL,
                    USUARIO, SENHA);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.exit(1);
        }
        return con;
    }

    public static void desconectar(Connection c) {
        try {
            c.close();
        } catch (SQLException ex) {
        }
    }

}
