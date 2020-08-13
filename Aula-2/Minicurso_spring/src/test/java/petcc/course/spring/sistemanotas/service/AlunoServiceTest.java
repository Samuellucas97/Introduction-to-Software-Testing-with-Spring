package petcc.course.spring.sistemanotas.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.repository.AlunoRepository;
import petcc.course.spring.sistemanotas.util.AlunoCreator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste da classe AlunoService")
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoServiceSUT;

    @Mock
    private AlunoRepository alunoRepositoryMock;

    @Test
    @DisplayName("listAll retorna uma lista de alunos quando bem sucedido")
    public void listAll_ReturnAlunoList_WhenSuccessful() {

        // CONFIGURAÇÃO
        Aluno expectedAluno = AlunoCreator.creatingValidAluno();
        when(alunoServiceSUT.findAll()).
                thenReturn( List.of(AlunoCreator.creatingValidAluno()));

        // EXECUÇÃO

        List<Aluno> resultAlunoList = alunoServiceSUT.findAll();

        // VERIFICAÇÃO

        assertThat(resultAlunoList).isNotEmpty();
        assertThat(resultAlunoList).contains(expectedAluno);
    }

    @Test
    @DisplayName("listAll retorna uma lista vazia quando não houver aluno")
    public void listAll_ReturnEmptyList_WhenThereIsNotAluno() {

        // CONFIGURAÇÃO

        // EXECUÇÃO

        List<Aluno> resultAlunoList = alunoServiceSUT.findAll();

        // VERIFICAÇÃO

        assertThat(resultAlunoList).isEmpty();
    }


    @Test
    @DisplayName("findByName retorna uma lista de alunos com o correspondente nome")
    public void findByName_ReturnListOfAluno_WhenSuccessful() {

        // CONFIGURAÇÃO

        Aluno aluno = AlunoCreator.creatingValidAluno();
        String expectedNameAluno = aluno.getNome();

        when(alunoServiceSUT.findByName(any()))
                .thenReturn(List.of(aluno));
        // EXECUÇÃO

        List<Aluno> resultAlunoList = alunoServiceSUT.findByName("fake");
        List<String> resultNomeOfAlunoList = resultAlunoList.stream()
                .map(Aluno::getNome).collect(Collectors.toList());

        // VERIFICAÇÃO

        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultNomeOfAlunoList).contains(expectedNameAluno)
        );
    }

    @Test
    public void findById_ReturnAluno_WhenSuccessful() {

        /// CONFIGURAÇÃO
        Aluno aluno = AlunoCreator.creatingValidAluno();
        Integer expectedIdAluno = aluno.getId();

        when(alunoRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(aluno));

        /// EXECUÇÃO
        Aluno resultAluno = alunoServiceSUT.findById(1);

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getId()).isNotNull(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    public void delete_RemoveAluno_WhenSuccessful(){

        /// CONFIGURAÇÃO
        When
        /// EXECUÇÃO
        alunoServiceSUT.delete();
        /// VERIFICAÇÃO

    }

}