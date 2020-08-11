package challenge;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	private static final String FILE_PATH = "src/main/resources/data.csv";
	private CSVReader csvReader;
	private List<String> cabecalho;
	private List<Jogador> jogadores = new ArrayList<>();

	//Primeira acao
	{
		try {
			csvReader = new CSVReader(new FileReader(FILE_PATH));
			cabecalho = Arrays.asList(csvReader.readNext());
			criarJogadores();
			csvReader.close();
		} catch (IOException | CsvValidationException e){
			e.printStackTrace();
		}
	}

	private void criarJogadores() throws IOException, CsvValidationException {
		String [] linhaAtual;
		while ((linhaAtual = csvReader.readNext()) != null) {
			jogadores.add(new Jogador(
					linhaAtual[getIndiceColunaCabecalho("full_name")],
					linhaAtual[getIndiceColunaCabecalho("club")],
					linhaAtual[getIndiceColunaCabecalho("age")],
					linhaAtual[getIndiceColunaCabecalho("birth_date")],
					linhaAtual[getIndiceColunaCabecalho("nationality")],
					linhaAtual[getIndiceColunaCabecalho("eur_wage")],
					linhaAtual[getIndiceColunaCabecalho("eur_release_clause")]
					));
		}
	}

	private int getIndiceColunaCabecalho(String nomeColuna){
		try {
			return cabecalho.indexOf(nomeColuna);
		} catch (NullPointerException e){
			throw new NullPointerException("Coluna não encontrada");
		}
	}


	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		int qtd = (int) jogadores.parallelStream().map(Jogador::getNationality).distinct().count();
		return qtd;
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		int qtd = (int) jogadores.parallelStream().filter(jogador -> !jogador.getClub().isEmpty())
				.map(Jogador::getClub).distinct().count();
		return qtd;
	}

	// Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		return jogadores.parallelStream().map(Jogador::getFullName).limit(20).collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		return jogadores.parallelStream().sorted(Comparator.comparing(Jogador::getReleaseClause).reversed())
				.map(Jogador :: getFullName).limit(10).collect(Collectors.toList());
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {

		return jogadores.parallelStream().sorted(Comparator.comparing(this::getIdadeEmDias)
				.thenComparing(Jogador::getWage).reversed()).map(Jogador::getFullName).limit(10)
				.collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		return jogadores.parallelStream().collect(
				Collectors.groupingBy(Jogador::getAge, Collectors.reducing(0, e -> 1,Integer::sum)));
	}

	private Long getIdadeEmDias(Jogador jogador){
		return ChronoUnit.DAYS.between(LocalDate.parse(jogador.getBirthDate()),LocalDate.now());
	}

}
