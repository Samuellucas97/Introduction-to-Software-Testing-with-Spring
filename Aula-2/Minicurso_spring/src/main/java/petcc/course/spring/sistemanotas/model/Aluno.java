package petcc.course.spring.sistemanotas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "O nome do aluno não pode ser nulo")
    @NotEmpty(message = "O nome do aluno não pode ser vazio")
    private String nome;
    @CPF(message = "O CPF do aluno é inválido")
    @Column(unique = true)
    private String cpf;
    @Email(message = "O email do aluno é inválido")
    @Column(unique = true)
    private String email;
    @NotNull(message = "O curso do aluno não pode ser nulo")
    private String curso;
}