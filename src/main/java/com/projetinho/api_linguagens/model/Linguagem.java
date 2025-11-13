package com.projetinho.api_linguagens.model;

import jakarta.persistence.*;
import lombok.*;

@Getter //Cria get e set automatico
@Setter
@AllArgsConstructor //Cria contrutor com todos os atributos
@NoArgsConstructor  //Cria contrutor vazio
@Builder
@Table(name = "linguagem")
@Entity
public class Linguagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Cria id automaticamente
    private Integer id;

    //Todos esses atributos nao precisam do @Column pois não tem especificações
    //Mas se caso tivesse precisaria, como não tem, o JPA coloca automaticamente
    private String nome; //Quando não é definido o tamanho o padrão é 255
    private String criador;
    private Integer anoCriacao;
    private String paradigma;

    @Column(length = 2000) //Números de caracter que vai ter a coluna de descriação
    private String descricao;

    private String Foto;

}
