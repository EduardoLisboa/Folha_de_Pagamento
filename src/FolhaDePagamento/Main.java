package FolhaDePagamento;

import java.util.Scanner;

public class Main {
    private static String data_hoje;

    private static String[][] funcs = new String[1000][15];
    private static int indice_funcs = 0;

    // [i][0]  = Nome
    // [i][1]  = Endereço
    // [i][2]  = Tipo de funcionário (1 - Horário, 2 - Assalariado, 3 - Comissionado)
    // [i][3]  = Valor Hora
    // [i][4]  = Salário
    // [i][5]  = Comissão
    // [i][6]  = Ativo
    // [i][7]  = Pertence à sindicato
    // [i][8]  = Taxa do sindicato
    // [i][9]  = ID
    // [i][10] = Forma de pagamento (1 - Depósito bancário, 2 - Cheque em mãos, 3 - Cheque pelo correio)
    // [i][11] = Dia preferido (Segunda a Sexta)
    // [i][12] = Tipo de pagamento (1 - Semanal, 2 - Quinzenal, 3 - Mensal)
    // [i][13] = Próximo pagamento
    // [i][14] = Último pagamento

    private static String[][] vendas_realizadas = new String[5000][4];
    private static int indice_vendas_realizadas = 0;

    // [i][0] = ID do funcionário
    // [i][1] = Valor
    // [i][2] = Data da venda
    // [i][3] = Contabilizado

    private static String[][] cartoes_ponto = new String[5000][5];
    private static int indice_cartoes_ponto = 0;

    // [i][0] = ID
    // [i][1] = Data ponto
    // [i][2] = Hora entrada
    // [i][3] = Hora saída
    // [i][4] = Contabilizado

    private static String[][] taxas_de_servicos = new String[5000][3];
    private static int indice_taxas_de_servicos = 0;

    // [i][0] = ID
    // [i][1] = Valor da taxa
    // [i][2] = Contabilizado

    private static String[] semana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};


    private static void passar_dia() {
        // Scanner input = new Scanner(System.in);
        // System.out.print("Data de hoje (dd/mm/aa): ");
        // data_hoje = input.nextLine();

        String data_final;
        String[] data = data_hoje.split("/");
        int dia_hoje = Integer.parseInt(data[0]);
        int mes_hoje = Integer.parseInt(data[1]);
        int ano_hoje = Integer.parseInt(data[2]);

        if(mes_hoje == 1 || mes_hoje == 3 || mes_hoje == 5 || mes_hoje == 7 || mes_hoje == 8 || mes_hoje == 10) {
            if(dia_hoje + 1 > 31) {
                dia_hoje = (dia_hoje + 1) - 31;
                mes_hoje += 1;
            } else {
                dia_hoje = (dia_hoje + 1);
            }
        } else if(mes_hoje == 4 || mes_hoje == 6 || mes_hoje == 9 || mes_hoje == 11) {
            if(dia_hoje + 1 > 30) {
                dia_hoje = (dia_hoje + 1) - 30;
                mes_hoje += 1;
            } else {
                dia_hoje = (dia_hoje + 1);
            }
        } else if(mes_hoje == 2) {
            if(dia_hoje + 1 > 28) {
                dia_hoje = (dia_hoje + 1) - 28;
                mes_hoje += 1;
            } else {
                dia_hoje = (dia_hoje + 7);
            }
        } else { // Mês == 12
            if (dia_hoje + 1 > 31) {
                dia_hoje = (dia_hoje + 1) - 31;
                mes_hoje = 1;
                ano_hoje += 1;
            } else {
                dia_hoje = (dia_hoje + 1);
            }
        }

        if(mes_hoje < 10) {
            if (dia_hoje < 10) {
                data_final = "0" + dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
            } else {
                data_final = dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
            }
        } else {
            if (dia_hoje < 10) {
                data_final = "0" + dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
            } else {
                data_final = dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
            }
        }

        data_hoje = data_final;
        rodar_folha_de_pagamentos();

    } // Fim de Passar Dia

    /* ----------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    /* -------------------------------------- MENUS -------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    private static void menus(int opc) {
        switch(opc) {
            case 0: // Menu principal
                System.out.println("\n-=-=- FOLHA DE PAGAMENTO -=-=-");
                System.out.println("1  - Funcionários");
                System.out.println("2  - Cartão Ponto");
                System.out.println("3  - Venda");
                System.out.println("4  - Taxa de Serviço");
                System.out.println("5  - Rodar Folha de Pagamento");
                System.out.println("6  - Agenda de Pagamentos");
                System.out.println("7  - Undo");
                System.out.println("8  - Redo");
                System.out.println("9  - Passar para o próximo dia");
                System.out.println("10 - Sair");
                System.out.print("--> ");
                break;
            case 1: // Menu de funcionários
                System.out.println("\n-=-=- FUNCIONÁRIOS -=-=-");
                System.out.println("1 - Adicionar Funcionário");
                System.out.println("2 - Remover Funcionário");
                System.out.println("3 - Editar Funcionário");
                System.out.println("4 - Listar Funcionários");
                System.out.println("5 - Retornar");
                System.out.print("--> ");
                break;
            case 2: // Menu de cartões ponto
                System.out.println("\n-=-=- CARTÃO PONTO -=-=-");
                System.out.println("1 - Bater Cartão Ponto Entrada");
                System.out.println("2 - Bater Cartão Ponto Saída");
                System.out.println("3 - Listar Cartões Ponto");
                System.out.println("4 - Retornar");
                System.out.print("--> ");
                break;
            case 3: // Menu de vendas
                System.out.println("\n-=-=- VENDAS -=-=-");
                System.out.println("1 - Lançar Venda");
                System.out.println("2 - Listar Vendas");
                System.out.println("3 - Retornar");
                System.out.print("--> ");
                break;
            case 4: // Menu de taxas de serviço
                System.out.println("\n-=-=- TAXA DE SERVIÇO -=-=-");
                System.out.println("1 - Lançar Taxa de Serviço");
                System.out.println("2 - Listar Taxas de Serviço");
                System.out.println("3 - Retornar");
                System.out.print("--> ");
                break;
            case 6: // Menu de agendas de pagamento
                System.out.println("\n-=-=- AGENDAS DE PAGAMENTO -=-=-");
                System.out.println("1 - Listar Agendas de Pagamento");
                System.out.println("2 - Retornar");
                System.out.print("--> ");
                break;
            default:
                System.out.println("\nMenu inexistente!\n");
        }
    } // Fim de "menus"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* --------------------------------------- MAIN --------------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String data_entrada;
        while(true) {
            System.out.print("Data de hoje (dd/mm/aa): ");
            data_entrada = input.nextLine();
            if(data_entrada.length() == 8) {
                String[] data_entrada_split = data_entrada.split("/");
                if(data_entrada_split.length == 3) {
                    break;
                } else {
                    System.out.println("Formato inválido!");
                }
            } else {
                System.out.println("Entrada inválida!");
            }
        }
        data_hoje = data_entrada;

        while(true) {
            System.out.printf("\nData de hoje: %s", data_hoje);
            menus(0);

            String opc;
            while(true){
                opc = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opc);
                    if(opcao_int > 0 && opcao_int < 11) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opcao = Integer.parseInt(opc);

            if(opcao == 10) {
                System.out.println("\nAté logo!\n");
                System.exit(0);
            }

            switch(opcao) {
                case 1:
                    funcionarios();
                    break;
                case 2:
                    cartao_ponto();
                    break;
                case 3:
                    vendas();
                    break;
                case 4:
                    taxa_de_servico();
                    break;
                case 5:
                    rodar_folha_de_pagamentos();
                    break;
                case 6:
                    agendas_de_pagamento();
                    break;
                case 7:
                    System.out.println("\nUNDO");
                    // undo();
                    break;
                case 8:
                    System.out.println("\nREDO");
                    // redo();
                    break;
                case 9:
                    passar_dia();
                    break;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!");
            }
        }

    } // Fim da Main


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ----------------------------------- FUNCIONÁRIOS ----------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */

    // Apenas direciona para as devidas funções de acordo com a entrada do usuário
    private static void funcionarios() {
        while(true) {
            menus(1);
            Scanner input = new Scanner(System.in);
            String opcao;
            while(true){
                opcao = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opcao);
                    if(opcao_int > 0 && opcao_int < 6) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opc = Integer.parseInt(opcao);

            switch(opc) {
                case 1:
                    adicionar_funcionario();
                    break;
                case 2:
                    remover_funcionario();
                    break;
                case 3:
                    editar_funcionario(-1);
                    break;
                case 4:
                    listar_funcionario();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "funcionarios"

    // Adiciona os novos funcionários
    private static void adicionar_funcionario() {
        Scanner input = new Scanner(System.in);

        String entrada;
        while(true) {
            System.out.print("\nNome: ");
            entrada = input.nextLine();
            if(!entrada.isEmpty()) {
                break;
            } else {
                System.out.print("Nome inválido!");
            }
        }
        funcs[indice_funcs][0] = entrada;

        while(true) {
            System.out.print("Endereço: ");
            entrada = input.nextLine();
            if(!entrada.isEmpty()) {
                break;
            } else {
                System.out.println("Endereço inválido!");
            }
        }
        funcs[indice_funcs][1] = entrada;

        while(true) {
            int int_entrada;
            System.out.println("Tipo de funcionário: ");
            System.out.println("1 - Horário");
            System.out.println("2 - Assalariado");
            System.out.println("3 - Comissionado");
            System.out.print("--> ");
            entrada = input.nextLine();
            try {
                int_entrada = Integer.parseInt(entrada);
                if(int_entrada > 0 && int_entrada < 4) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e){
                System.out.println("Apenas números!");
            }
        }
        funcs[indice_funcs][2] = entrada;

        switch(entrada) {
            case "1":
                String valor_hora;
                while(true) {
                    System.out.print("Valor da hora: R$");
                    valor_hora = input.nextLine();
                    try {
                        float valor_hora_float = Float.parseFloat(valor_hora);
                        if(valor_hora_float > 0) {
                            break;
                        } else {
                            System.out.println("Valor da hora não pode ser menor ou igual a 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números ou pontos flutuantes!");
                    }
                }
                funcs[indice_funcs][3] = valor_hora;
                funcs[indice_funcs][4] = "-1";
                funcs[indice_funcs][5] = "-1";
                break;
            case "2":
                String salario;
                while(true) {
                    System.out.print("Salário: R$");
                    salario = input.nextLine();
                    try {
                        float salario_float = Float.parseFloat(salario);
                        if(salario_float > 0) {
                            break;
                        } else {
                            System.out.println("Salário não pode menor ou igual a 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números ou pontos flutuantes!");
                    }
                }
                funcs[indice_funcs][3] = "-1";
                funcs[indice_funcs][4] = salario;
                funcs[indice_funcs][5] = "-1";
                break;
            case "3":
                String sal;
                while(true) {
                    System.out.print("Salário: R$");
                    sal = input.nextLine();
                    try {
                        float sal_float = Float.parseFloat(sal);
                        if (sal_float > 0) {
                            break;
                        } else {
                            System.out.println("Salário não pode ser menor ou igual a 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números ou pontos flutuantes!");
                    }
                }

                String comissao;
                while(true) {
                    System.out.print("Comissão (sem '%'): ");
                    comissao = input.nextLine();
                    try {
                        float comissao_float = Float.parseFloat(comissao);
                        if(comissao_float > 0) {
                            break;
                        } else {
                            System.out.println("Comissão não pode ser menor ou igual a 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números ou pontos flutuantes!");
                    }
                }
                funcs[indice_funcs][3] = "-1";
                funcs[indice_funcs][4] = sal;
                funcs[indice_funcs][5] = comissao;
                break;
        }

        funcs[indice_funcs][6] = "s";

        while(true) {
            System.out.print("Pertence a Sindicato (s/n): ");
            entrada = input.nextLine();
            if(entrada.length() > 1 || (!entrada.equals("s") && !entrada.equals("n"))) {
                System.out.println("Apenas \"s\" ou \"n\"!");
            }
            else {
                break;
            }
        }
        funcs[indice_funcs][7] = entrada;

        if(entrada.equals("s")) {
            String taxa_sindicato;
            while(true) {
                System.out.print("Taxa do Sindicato: R$");
                taxa_sindicato = input.nextLine();
                try {
                    float taxa_sindicat_float = Float.parseFloat(taxa_sindicato);
                    if(taxa_sindicat_float > 0) {
                        break;
                    } else {
                        System.out.println("A taxa do sindicato não pode ser menor ou igual a 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números ou pontos flutuantes!");
                }
            }

            funcs[indice_funcs][8] = taxa_sindicato;
        } else {
            funcs[indice_funcs][8] = "-1";
        }

        funcs[indice_funcs][9] = Integer.toString(indice_funcs + 1);

        while(true) {
            System.out.print("Forma de pagamento:\n");
            System.out.print("1 - Depósito bancário\n");
            System.out.print("2 - Cheque em mãos\n");
            System.out.print("3 - Cheque pelos correios\n");
            System.out.print("--> ");
            entrada = input.nextLine();
            try {
                int int_entrada = Integer.parseInt(entrada);
                if(int_entrada > 0 && int_entrada < 4){
                    break;
                } else {
                    System.out.println("Valor inválido!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }
        funcs[indice_funcs][10] = entrada;

        while(true) {
            System.out.print("Dia preferido de pagamento:\n");
            System.out.print("2 - Segunda\n");
            System.out.print("3 - Terça\n");
            System.out.print("4 - Quarta\n");
            System.out.print("5 - Quinta\n");
            System.out.print("6 - Sexta\n");
            System.out.print("--> ");
            entrada = input.nextLine();
            try {
                int int_entrada = Integer.parseInt(entrada);
                if(int_entrada > 1 && int_entrada < 7) {
                    break;
                } else {
                    System.out.println("Valor inválido!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }
        funcs[indice_funcs][11] = entrada;

        while(true) {
            System.out.print("Tipo de pagamento:\n");
            System.out.print("1 - Semanal\n");
            System.out.print("2 - Quinzenal\n");
            System.out.print("3 - Mensal\n");
            System.out.print("--> ");
            entrada = input.nextLine();
            try {
                int int_entrada = Integer.parseInt(entrada);
                if(int_entrada > 0 && int_entrada < 4) {
                    break;
                } else {
                    System.out.println("Valor inválido!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }
        funcs[indice_funcs][12] = entrada;

        String data_final;
        int int_entrada = Integer.parseInt(entrada);
        String[] data = data_hoje.split("/");
        int dia_hoje = Integer.parseInt(data[0]);
        int mes_hoje = Integer.parseInt(data[1]);
        int ano_hoje = Integer.parseInt(data[2]);

        boolean bool_mes = mes_hoje == 1 || mes_hoje == 3 || mes_hoje == 5 || mes_hoje == 7 || mes_hoje == 8 || mes_hoje == 10;
        if(int_entrada == 1) {
            if(bool_mes) {
                if(dia_hoje + 7 > 31) {
                    dia_hoje = (dia_hoje + 7) - 31;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 7);
                }
            } else if(mes_hoje == 4 || mes_hoje == 6 || mes_hoje == 9 || mes_hoje == 11) {
                if(dia_hoje + 7 > 30) {
                    dia_hoje = (dia_hoje + 7) - 30;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 7);
                }
            } else if(mes_hoje == 2) {
                if(dia_hoje + 7 > 28) {
                    dia_hoje = (dia_hoje + 7) - 28;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 7);
                }
            } else { // Mês == 12
                if (dia_hoje + 7 > 31) {
                    dia_hoje = (dia_hoje + 7) - 31;
                    mes_hoje = 1;
                    ano_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 7);
                }
            }
        } else if(int_entrada == 2) {
            if(bool_mes) {
                if(dia_hoje + 15 > 31) {
                    dia_hoje = (dia_hoje + 15) - 31;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 15);
                }
            } else if(mes_hoje == 4 || mes_hoje == 6 || mes_hoje == 9 || mes_hoje == 11) {
                if(dia_hoje + 15 > 30) {
                    dia_hoje = (dia_hoje + 15) - 30;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 15);
                }
            } else if(mes_hoje == 2) {
                if(dia_hoje + 15 > 28) {
                    dia_hoje = (dia_hoje + 15) - 28;
                    mes_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 15);
                }
            } else { // Mês == 12
                if (dia_hoje + 15 > 31) {
                    dia_hoje = (dia_hoje + 15) - 31;
                    mes_hoje = 1;
                    ano_hoje += 1;
                } else {
                    dia_hoje = (dia_hoje + 15);
                }
            }
        } else { // Mensal
            if(mes_hoje == 12) {
                mes_hoje = 1;
                ano_hoje += 1;
            } else {
                mes_hoje += 1;
            }
        }

        if(mes_hoje < 10) {
            if (dia_hoje < 10) {
                data_final = "0" + dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
            } else {
                data_final = dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
            }
        } else {
            if (dia_hoje < 10) {
                data_final = "0" + dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
            } else {
                data_final = dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
            }
        }

        funcs[indice_funcs][13] = data_final;
        funcs[indice_funcs][14] = "Ainda não efetuado";

        indice_funcs++;

    } // Fim de "adicionar_funcionario"

    // Remove o funcionário escolhido pelo usuário
    private static void remover_funcionario() {
        int indice_funcionario = localizar_funcionario();
        if(indice_funcionario == -1) {
            System.out.println("\nUsuário não encontrado!");
            return;
        }

        System.out.println("\nUsuário localizado!\n");
        int id = Integer.parseInt(funcs[indice_funcionario][9]);
        System.out.printf("ID: %d\n", id);
        System.out.printf("Nome: %s\n", funcs[indice_funcionario][0]);

        System.out.print("\nConfirmar remoção de funcionário? (s/n) ");
        Scanner input = new Scanner(System.in);
        String confirmar;
        while(true) {
            confirmar = input.nextLine();
            if(confirmar.length() > 1 || (!confirmar.equals("s") && !confirmar.equals("n"))) {
                System.out.println("Entrada inválida!");
            } else {
                break;
            }
        }
        if(confirmar.equals("s")) {
            funcs[indice_funcionario][6] = "n";
        }

    } // Fim de "remover_funcionario"

    // Edita todas as informações de um determinado funcionário escolhido pelo usuário
    private static void editar_funcionario(int func) {
        int indice_funcionario;
        if(func == -1) {
            indice_funcionario = localizar_funcionario();
        } else {
            indice_funcionario = func;
        }

        imprimir_funcionario(indice_funcionario);

        System.out.println("\nO que você deseja editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Endereço");
        System.out.println("3 - Tipo de Funcionário");
        System.out.println("4 - Sindicato");
        System.out.println("5 - Forma de pagamento");
        System.out.println("6 - Dia preferido de pagamento");
        System.out.println("7 - Tipo de pagamento");
        System.out.println("8 - Retornar");
        System.out.print("--> ");
        Scanner input_opc = new Scanner(System.in);
        int opcao = input_opc.nextInt();

        Scanner input = new Scanner(System.in);
        switch(opcao) {
            case 1:
                String novo_nome;
                while(true) {
                    System.out.print("\nNovo nome: ");
                    novo_nome = input.nextLine();
                    if(!novo_nome.isEmpty()) {
                        break;
                    } else {
                        System.out.print("Nome inválido!");
                    }
                }
                funcs[indice_funcionario][0] = novo_nome;

                System.out.println("\nNome alterado com sucesso!");
                break;
            case 2:
                String novo_endereco;
                while(true) {
                    System.out.print("\nNovo endereço: ");
                    novo_endereco = input.nextLine();
                    if(!novo_endereco.isEmpty()) {
                        break;
                    } else {
                        System.out.print("Endereço inválido!");
                    }
                }
                funcs[indice_funcionario][1] = novo_endereco;

                System.out.println("\nEndereço alterado com sucesso!");
                break;
            case 3:
                String novo_tipo;
                while(true) {
                    int int_novo_tipo;
                    System.out.println("Novo tipo de funcionário: ");
                    System.out.println("1 - Horário");
                    System.out.println("2 - Assalariado");
                    System.out.println("3 - Comissionado");
                    System.out.print("--> ");
                    novo_tipo = input.nextLine();
                    try {
                        int_novo_tipo = Integer.parseInt(novo_tipo);
                        if(int_novo_tipo > 0 && int_novo_tipo < 4) {
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Apenas números!");
                    }
                }
                funcs[indice_funcionario][2] = novo_tipo;
                switch(novo_tipo) {
                    case "1":
                        String novo_valor_hora;
                        while(true) {
                            System.out.print("Novo valor da hora: R$");
                            novo_valor_hora = input.nextLine();
                            try {
                                float valor_hora_float = Float.parseFloat(novo_valor_hora);
                                if(valor_hora_float > 0) {
                                    break;
                                } else {
                                    System.out.println("Valor da hora não pode ser menor ou igual a 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Apenas números ou pontos flutuantes!");
                            }
                        }
                        funcs[indice_funcionario][3] = novo_valor_hora;
                        funcs[indice_funcionario][4] = "-1";
                        funcs[indice_funcionario][5] = "-1";
                        break;
                    case "2":
                        String novo_salario;
                        while(true) {
                            System.out.print("Salário: R$");
                            novo_salario = input.nextLine();
                            try {
                                float salario_float = Float.parseFloat(novo_salario);
                                if(salario_float > 0) {
                                    break;
                                } else {
                                    System.out.println("Salário não pode menor ou igual a 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Apenas números ou pontos flutuantes!");
                            }
                        }
                        funcs[indice_funcionario][3] = "-1";
                        funcs[indice_funcionario][4] = novo_salario;
                        funcs[indice_funcionario][5] = "-1";
                        break;
                    case "3":
                        String novo_sal;
                        while(true) {
                            System.out.print("Salário: R$");
                            novo_sal = input.nextLine();
                            try {
                                float sal_float = Float.parseFloat(novo_sal);
                                if (sal_float > 0) {
                                    break;
                                } else {
                                    System.out.println("Salário não pode ser menor ou igual a 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Apenas números ou pontos flutuantes!");
                            }
                        }

                        String nova_comissao;
                        while(true) {
                            System.out.print("Comissão (sem '%'): ");
                            nova_comissao = input.nextLine();
                            try {
                                float comissao_float = Float.parseFloat(nova_comissao);
                                if(comissao_float > 0) {
                                    break;
                                } else {
                                    System.out.println("Comissão não pode ser menor ou igual a 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Apenas números ou pontos flutuantes!");
                            }
                        }
                        funcs[indice_funcionario][3] = "-1";
                        funcs[indice_funcionario][4] = novo_sal;
                        funcs[indice_funcionario][5] = nova_comissao;
                        break;
                }

                System.out.println("\nTipo de funcionário alterado com sucesso!");
                break;
            case 4:

                String novo_sindicato;
                while(true) {
                    System.out.print("\nPertence a sindicato? (s/n) ");
                    novo_sindicato = input.nextLine();
                    if(novo_sindicato.length() > 1 || (!novo_sindicato.equals("s") && !novo_sindicato.equals("n"))) {
                        System.out.println("Apenas \"s\" ou \"n\"!");
                    } else {
                        break;
                    }
                }
                if(novo_sindicato.equals("s")) {
                    Scanner in = new Scanner(System.in);
                    String taxa_sindicato;
                    funcs[indice_funcionario][7] = "s";
                    while(true) {
                        System.out.print("Taxa de sindicato: R$");
                        taxa_sindicato = in.nextLine();
                        try {
                            float int_taxa_sindicato = Float.parseFloat(taxa_sindicato);
                            if(int_taxa_sindicato > 0) {
                                break;
                            } else {
                                System.out.println("Taxa do sindicato não pode ser menor ou igual a 0!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Apenas números ou pontos flutuantes!");
                        }
                    }
                    funcs[indice_funcionario][8] = taxa_sindicato;
                } else {
                    funcs[indice_funcionario][7] = "n";
                    funcs[indice_funcionario][8] = "-1";
                }
                System.out.println("\nSindicato alterado com sucesso!");
                break;
            case 5:
                Scanner in = new Scanner(System.in);
                String nova_forma;
                while(true) {
                    System.out.print("Forma de pagamento:\n");
                    System.out.print("1 - Depósito bancário\n");
                    System.out.print("2 - Cheque em mãos\n");
                    System.out.print("3 - Cheque pelos correios\n");
                    System.out.print("--> ");
                    nova_forma = in.nextLine();
                    try {
                        int int_nova_forma = Integer.parseInt(nova_forma);
                        if(int_nova_forma > 0 && int_nova_forma < 4) {
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números!");
                    }
                }
                funcs[indice_funcionario][10] = nova_forma;

                System.out.println("\nForma de pagamento alterada com sucesso!");
                break;
            case 6:
                Scanner inp = new Scanner(System.in);
                String novo_dia_preferido;
                while(true) {
                    System.out.print("Dia preferido de pagamento:\n");
                    System.out.print("2 - Segunda\n");
                    System.out.print("3 - Terça\n");
                    System.out.print("4 - Quarta\n");
                    System.out.print("5 - Quinta\n");
                    System.out.print("6 - Sexta\n");
                    System.out.print("--> ");
                    novo_dia_preferido = inp.nextLine();
                    try {
                        int int_novo_dia_preferido = Integer.parseInt(novo_dia_preferido);
                        if(int_novo_dia_preferido > 1 && int_novo_dia_preferido < 7) {
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números!");
                    }
                }
                funcs[indice_funcionario][11] = novo_dia_preferido;

                System.out.println("\nDia preferido de pagamento alterado com sucesso!");
                break;
            case 7:
                Scanner inpu = new Scanner(System.in);
                String novo_tipo_pagamento;
                while(true) {
                    System.out.print("Tipo de pagamento:\n");
                    System.out.print("1 - Semanal\n");
                    System.out.print("2 - Quinzenal\n");
                    System.out.print("3 - Mensal\n");
                    System.out.print("--> ");
                    novo_tipo_pagamento = inpu.nextLine();
                    try {
                        int int_novo_tipo_pagamento = Integer.parseInt(novo_tipo_pagamento);
                        if(int_novo_tipo_pagamento > 0 && int_novo_tipo_pagamento < 4) {
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números!");
                    }
                }
                funcs[indice_funcionario][12] = novo_tipo_pagamento;

                System.out.println("\nTipo de pagamento alterado com sucesso!");
                break;
            case 8:
                return;
        }

        Scanner in = new Scanner(System.in);
        String continuar;
        while(true) {
            System.out.print("\nDeseja editar algo mais? (s/n) ");
            continuar = in.nextLine();
            if(continuar.length() > 1 || (!continuar.equals("s") && !continuar.equals("n"))) {
                System.out.println("Apenas \"s\" ou \"n\"!");
            } else {
                break;
            }
        }
        if(continuar.equals("s")) {
            editar_funcionario(indice_funcionario);
        }

    } // Fim de "editar_funcionario"

    // Imprime as informações de um funcionário específico
    private static void imprimir_funcionario(int indice_funcionario) {
        int id = Integer.parseInt(funcs[indice_funcionario][9]);
        System.out.printf("\nID: %d\n", id);
        System.out.printf("Nome: %s\n", funcs[indice_funcionario][0]);
        System.out.printf("Endereço: %s\n", funcs[indice_funcionario][1]);
        System.out.print("Tipo de funcionário: ");
        if(funcs[indice_funcionario][2].equals("1")) {
            System.out.println("Horário");
            float valor_hora = Float.parseFloat(funcs[indice_funcionario][3]);
            System.out.printf("Valor da hora: R$%.2f\n", valor_hora);
        } else if(funcs[indice_funcionario][2].equals("2")) {
            System.out.println("Assalariado");
            float salario = Float.parseFloat(funcs[indice_funcionario][4]);
            System.out.printf("Salário: R$%.2f\n", salario);
        } else {
            System.out.println("Comissionado");
            float salario = Float.parseFloat(funcs[indice_funcionario][4]);
            System.out.printf("Salário: R$%.2f\n", salario);
            float comissao = Float.parseFloat(funcs[indice_funcionario][5]);
            System.out.printf("Comissão: %.1f%%\n", comissao);
        }
        System.out.print("Pertence a sindicato: ");
        if(funcs[indice_funcionario][7].equals("s")) {
            System.out.println("Sim");
            System.out.printf("Taxa do sindicato: R$%s\n", funcs[indice_funcionario][8]);
        } else {
            System.out.println("Não");
        }
        System.out.print("Forma de pagamento: ");
        if(funcs[indice_funcionario][10].equals("1")) {
            System.out.println("Depósito bancário");
        } else if(funcs[indice_funcionario][10].equals("2")) {
            System.out.println("Cheque em mãos");
        } else {
            System.out.println("Cheque pelos correios");
        }
        int dia = Integer.parseInt(funcs[indice_funcionario][11]);
        System.out.printf("Dia preferido de pagamento: %s\n", semana[dia - 2]);
        System.out.print("Tipo de pagamento: ");
        if(funcs[indice_funcionario][12].equals("1")) {
            System.out.println("Semanal");
        } else if(funcs[indice_funcionario][12].equals("2")) {
            System.out.println("Quinzenal");
        } else {
            System.out.println("Mensal");
        }
        System.out.printf("Próximo pagamento: %s\n", funcs[indice_funcionario][13]);
        System.out.printf("Último pagamento: %s\n", funcs[indice_funcionario][14]);
    } // Fim de "imprimir_funcionario"

    // Lista todas as informações de todos os funcionários
    private static void listar_funcionario() {
        for(int i = 0; i < indice_funcs; i++) {
            if(funcs[i][6].equals("s")) {
                System.out.printf("\nID: %d\n", Integer.parseInt(funcs[i][9]));
                System.out.printf("Nome: %s\n", funcs[i][0]);
                System.out.printf("Endereço: %s\n", funcs[i][1]);
                System.out.print("Tipo de funcionário: ");
                if(funcs[i][2].equals("1")) {
                    System.out.println("Horário");
                    float valor_hora = Float.parseFloat(funcs[i][3]);
                    System.out.printf("Valor da hora: R$%.2f\n", valor_hora);
                } else if(funcs[i][2].equals("2")) {
                    System.out.println("Assalariado");
                    float salario = Float.parseFloat(funcs[i][4]);
                    System.out.printf("Salário: R$%.2f\n", salario);
                } else {
                    System.out.println("Comissionado");
                    float salario = Float.parseFloat(funcs[i][4]);
                    System.out.printf("Salário: R$%.2f\n", salario);
                    float comissao = Float.parseFloat(funcs[i][5]);
                    System.out.printf("Comissão: %.1f%%\n", comissao);
                }
                System.out.print("Pertence a sindicato: ");
                if(funcs[i][7].equals("s")) {
                    System.out.println("Sim");
                    System.out.printf("Taxa do sindicato: R$%s\n", funcs[i][8]);
                } else {
                    System.out.println("Não");
                }
                System.out.print("Forma de pagamento: ");
                if(funcs[i][10].equals("1")) {
                    System.out.println("Depósito bancário");
                } else if(funcs[i][10].equals("2")) {
                    System.out.println("Cheque em mãos");
                } else {
                    System.out.println("Cheque pelos correios");
                }
                int dia = Integer.parseInt(funcs[i][11]);
                System.out.printf("Dia preferido de pagamento: %s\n", semana[dia - 2]);
                System.out.print("Tipo de pagamento: ");
                if(funcs[i][12].equals("1")) {
                    System.out.println("Semanal");
                } else if(funcs[i][12].equals("2")) {
                    System.out.println("Quinzenal");
                } else {
                    System.out.println("Mensal");
                }
                System.out.printf("Próximo pagamento: %s\n", funcs[i][13]);
                System.out.printf("Último pagamento: %s\n", funcs[i][14]);
            }
        }
    } // Fim de "listar_funcionario"

    // Localiza no sistema o funcionário pelo ID e retorna seu índice
    private static int localizar_funcionario() {

        Scanner input = new Scanner(System.in);
        String id_string;
        while(true) {
            System.out.print("\nInsira o ID do funcionário: ");
            id_string = input.nextLine();
            try {
                int int_id = Integer.parseInt(id_string);
                if(int_id > 0) {
                    break;
                } else {
                    System.out.println("ID não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }

        int id = Integer.parseInt(id_string);
        if(id <= indice_funcs) {
            return id - 1; // Retorna o índice do funcionário no array
        } else {
            return -1; // Não encontrou o empregado
        }

    } // Fim de "localizar_empregado"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ----------------------------------- CARTÃO PONTO ----------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void cartao_ponto() {
        while(true) {
            menus(2);
            Scanner input = new Scanner(System.in);
            String opcao;
            while(true){
                opcao = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opcao);
                    if(opcao_int > 0 && opcao_int < 5) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opc = Integer.parseInt(opcao);

            switch(opc) {
                case 1:
                    entrada_cartao_ponto();
                    break;
                case 2:
                    saida_cartao_ponto();
                    break;
                case 3:
                    listar_cartoes_ponto();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "cartao_ponto"

    private static void entrada_cartao_ponto() {
        Scanner input = new Scanner(System.in);
        String id_string;
        while(true) {
            System.out.print("\nInsira o ID do funcionário: ");
            id_string = input.nextLine();
            try {
                int int_id = Integer.parseInt(id_string);
                if(int_id > 0) {
                    break;
                } else {
                    System.out.println("ID não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }

        int id_int = Integer.parseInt(id_string);
        if(funcs[id_int - 1][0] == null) {
            System.out.println("\nFuncionário não cadastrado!");
            return;
        }

        if(indice_cartoes_ponto != 0) {
            for(int i = 0; i < indice_cartoes_ponto; i++) {
                if(cartoes_ponto[i][0].equals(id_string) && cartoes_ponto[i][1].equals(data_hoje)) {
                    System.out.println("\nFuncionário já bateu ponto de entrada!");
                    return;
                }
            }
        }

        cartoes_ponto[indice_cartoes_ponto][0] = id_string;

        cartoes_ponto[indice_cartoes_ponto][1] = data_hoje;

        String hora_entrada;
        while(true) {
            System.out.print("Hora de entrada (hh:mm): ");
            hora_entrada = input.nextLine();
            String[] hora_dividida = hora_entrada.split(":");
            if(hora_dividida.length == 2) {
                try {
                    int int_hora = Integer.parseInt(hora_dividida[0]);
                    int int_minuto = Integer.parseInt(hora_dividida[1]);
                    if((int_hora >= 0 && int_hora <= 23) && (int_minuto >= 0 && int_minuto <= 59)) {
                        break;
                    } else {
                        System.out.println("Hora inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números e \":\"!");
                }
            } else {
                System.out.println("Formato inválido!");
            }
        }

        cartoes_ponto[indice_cartoes_ponto][2] = hora_entrada;

        cartoes_ponto[indice_cartoes_ponto][3] = "-1";
        cartoes_ponto[indice_cartoes_ponto][4] = "n";

        indice_cartoes_ponto++;
    } // Fim de "entrada_cartao_ponto"

    private static void saida_cartao_ponto() {
        Scanner input = new Scanner(System.in);
        String id_string;
        while(true) {
            System.out.print("\nInsira o ID do funcionário: ");
            id_string = input.nextLine();
            try {
                int int_id = Integer.parseInt(id_string);
                if(int_id > 0) {
                    break;
                } else {
                    System.out.println("ID não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }

        for(int i = 0; i < indice_cartoes_ponto; i++) {
            if(cartoes_ponto[i][0].equals(id_string) && cartoes_ponto[i][3].equals("-1") && cartoes_ponto[i][1].equals(data_hoje)) {

                String hora_saida;
                while(true) {
                    System.out.print("Hora de saída (hh:mm): ");
                    hora_saida = input.nextLine();
                    String[] hora_dividida = hora_saida.split(":");
                    if(hora_dividida.length == 2) {
                        try {
                            int int_hora = Integer.parseInt(hora_dividida[0]);
                            int int_minuto = Integer.parseInt(hora_dividida[1]);
                            if((int_hora >= 0 && int_hora <= 23) && (int_minuto >= 0 && int_minuto <= 59)) {
                                break;
                            } else {
                                System.out.println("Hora inválida!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Apenas números e \":\"!");
                        }
                    } else {
                        System.out.println("Formato inválido!");
                    }
                }
                cartoes_ponto[i][3] = hora_saida;
                break;
            } else if(cartoes_ponto[i][0].equals(id_string) && !cartoes_ponto[i][3].equals("-1") && cartoes_ponto[i][1].equals(data_hoje)){
                System.out.println("\nFuncionário já bateu o ponto de saída!");
                break;
            }
        }
    } // Fim de "saida_cartao_ponto"

    private static void listar_cartoes_ponto() {
        for(int i = 0; i < indice_cartoes_ponto; i++) {
            System.out.printf("\nID: %s\n", cartoes_ponto[i][0]);
            int id = Integer.parseInt(cartoes_ponto[i][0]);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            System.out.printf("Data do ponto: %s\n", cartoes_ponto[i][1]);
            System.out.printf("Hora de entrada: %s\n", cartoes_ponto[i][2]);

            if(!cartoes_ponto[i][3].equals("-1")) {
                System.out.printf("Hora de saída: %s\n", cartoes_ponto[i][3]);
            }
        }
    } // Fim de "listar_cartoes_ponto"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* -------------------------------------- VENDAS -------------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void vendas() {
        while(true) {
            menus(3);
            Scanner input = new Scanner(System.in);
            String opcao;
            while(true){
                opcao = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opcao);
                    if(opcao_int > 0 && opcao_int < 4) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opc = Integer.parseInt(opcao);

            switch(opc) {
                case 1:
                    // System.out.println("\nLANÇAR VENDA\n");
                    lancar_venda();
                    break;
                case 2:
                    // System.out.println("\nLISTAR VENDAS\n");
                    listar_vendas();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "vendas"

    private static void lancar_venda() {
        Scanner input = new Scanner(System.in);
        String id_string;
        while(true) {
            System.out.print("\nInsira o ID do funcionário: ");
            id_string = input.nextLine();
            try {
                int int_id = Integer.parseInt(id_string);
                if(int_id > 0) {
                    break;
                } else {
                    System.out.println("ID não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }

        int id_int = Integer.parseInt(id_string);
        if(funcs[id_int - 1][0] == null) {
            System.out.println("\nFuncionário não cadastrado!");
            return;
        }

        vendas_realizadas[indice_vendas_realizadas][0] = id_string;

        String valor;
        while(true) {
            System.out.print("Valor da venda: R$");
            valor = input.nextLine();
            try {
                float float_valor = Float.parseFloat(valor);
                if(float_valor > 0) {
                    break;
                } else {
                    System.out.println("Valor da venda não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números ou pontos flutuantes!");
            }
        }
        vendas_realizadas[indice_vendas_realizadas][1] = valor;

        String data;
        while(true) {
            System.out.print("Data da venda (dd/mm/aa): ");
            data = input.nextLine();
            if(data.length() == 8) {
                String[] data_split = data.split("/");
                if(data_split.length == 3) {
                    try {
                        int dia = Integer.parseInt(data_split[0]);
                        int mes = Integer.parseInt(data_split[1]);
                        int ano = Integer.parseInt(data_split[2]);
                        if((dia > 0 && dia < 31) && (mes > 0 && mes < 13) && (ano > 0)) {
                            break;
                        } else {
                            System.out.println("Data inválida!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números e \"/\"!");
                    }
                } else {
                    System.out.println("Formato inválido!");
                }
            } else {
                System.out.println("Entrada inválida!");
            }
        }
        vendas_realizadas[indice_vendas_realizadas][2] = data;

        vendas_realizadas[indice_vendas_realizadas][3] = "n";

        indice_vendas_realizadas++;
    } // Fim de "lançar_venda"

    private static void listar_vendas() {
        for(int i = 0; i < indice_vendas_realizadas; i++) {
            int id = Integer.parseInt(vendas_realizadas[i][0]);
            System.out.printf("\nID: %d\n", id);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            float valor = Float.parseFloat(vendas_realizadas[i][1]);
            System.out.printf("Valor da venda: R$%.2f\n", valor);
            System.out.printf("Data da venda: %s\n", vendas_realizadas[i][2]);
        }
    } // Fim de "listar_vendas"

    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* --------------------------------- TAXA DE SERVIÇOS --------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void taxa_de_servico() {
        while(true) {
            menus(4);
            Scanner input = new Scanner(System.in);
            String opcao;
            while(true){
                opcao = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opcao);
                    if(opcao_int > 0 && opcao_int < 4) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opc = Integer.parseInt(opcao);

            switch(opc) {
                case 1:
                    lancar_taxa_de_servico();
                    break;
                case 2:
                    listar_taxas_de_servico();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "taxa_de_servico"

    private static void lancar_taxa_de_servico() {
        Scanner input = new Scanner(System.in);
        String id_string;
        while(true) {
            System.out.print("\nInsira o ID do funcionário: ");
            id_string = input.nextLine();
            try {
                int int_id = Integer.parseInt(id_string);
                if(int_id > 0) {
                    break;
                } else {
                    System.out.println("ID não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }
        }

        int id_int = Integer.parseInt(id_string);
        if(funcs[id_int - 1][0] == null) {
            System.out.println("\nFuncionário não cadastrado!");
            return;
        }

        taxas_de_servicos[indice_taxas_de_servicos][0] = id_string;

        String valor_taxa;
        while(true) {
            System.out.print("Valor da taxa: R$");
            valor_taxa = input.nextLine();
            try {
                float float_taxa = Float.parseFloat(valor_taxa);
                if(float_taxa > 0) {
                    break;
                } else {
                    System.out.println("O valor da taxa não pode ser menor ou igual a 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números e pontos flutuantes!");
            }
        }

        taxas_de_servicos[indice_taxas_de_servicos][1] = valor_taxa;
        taxas_de_servicos[indice_taxas_de_servicos][2] = "n";

        indice_taxas_de_servicos++;
    } // Fim de "lancar_taxa_de_servico"

    private static void listar_taxas_de_servico() {
        for(int i = 0; i < indice_taxas_de_servicos; i++) {
            System.out.printf("\nID: %s\n", taxas_de_servicos[i][0]);
            int id = Integer.parseInt(taxas_de_servicos[i][0]);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            float valor = Float.parseFloat(taxas_de_servicos[i][1]);
            System.out.printf("Valor da taxa: R$%.2f\n", valor);
        }
    } // Fim de "listar_taxas_de_servico"

    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------- AGENDAS DE PAGAMENTO ------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void agendas_de_pagamento() {
        while(true) {
            menus(6);
            Scanner input = new Scanner(System.in);
            String opcao;
            while(true){
                opcao = input.nextLine();
                try {
                    int opcao_int = Integer.parseInt(opcao);
                    if(opcao_int > 0 && opcao_int < 3) {
                        break;
                    } else {
                        System.out.print("Valor inválido, tente novamente!\n--> ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Apenas números!\n--> ");
                }
            }
            int opc = Integer.parseInt(opcao);

            switch(opc) {
                case 1:
                    listar_agendas_de_pagamento();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "agendas_de_pagamento"

    private static void listar_agendas_de_pagamento() {
        System.out.println("AGENDAS DE PAGAMENTO");
        System.out.println("1 - Semanal");
        System.out.println("2 - Quinzenal");
        System.out.println("3 - Mensal");
    }

    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* -------------------------------- FOLHA DE PAGAMENTO -------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */

    private static void rodar_folha_de_pagamentos() {
        System.out.println("\nRODANDO FOLHA DE PAGAMENTOS\n");
        String[] funcs_pagar_hoje = new String[1000];
        int k = 0;
        for(int i = 0; i < indice_funcs; i++) {
            if(funcs[i][13].equals(data_hoje) && funcs[i][6].equals("s")) {
                funcs_pagar_hoje[k] = funcs[i][9];
                k++;
            }
        }

        if(k == 0) {
            System.out.println("Nenhum funcionário para receber hoje!");
            return;
        }

        float valor_calculado;
        for(int j = 0; j < k; j++) {
            valor_calculado = 0;
            int id = Integer.parseInt(funcs_pagar_hoje[j]);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);

            for(int tax = 0; tax < indice_taxas_de_servicos; tax++) {
                int id_taxa = Integer.parseInt(taxas_de_servicos[tax][0]);
                if(id_taxa == id && taxas_de_servicos[tax][2].equals("n")){
                    valor_calculado -= Float.parseFloat(taxas_de_servicos[tax][1]);
                    taxas_de_servicos[tax][2] = "s";
                }
            }

            if(funcs[id - 1][7].equals("s")) {
                valor_calculado -= Float.parseFloat(funcs[id - 1][8]);
            }

            if(funcs[id - 1][2].equals("1")) { // Horário
                int horas_trabalhadas = 0;
                for(int pont = 0; pont < indice_cartoes_ponto; pont++) {
                    int id_ponto = Integer.parseInt(cartoes_ponto[pont][0]);
                    if(id_ponto == id && cartoes_ponto[pont][4].equals("n") && !cartoes_ponto[pont][3].equals("-1")) {
                        String[] tempo_entrada = cartoes_ponto[pont][2].split(":");
                        int hora_entrada = Integer.parseInt(tempo_entrada[0]);
                        int minuto_entrada = Integer.parseInt(tempo_entrada[1]);

                        String[] tempo_saida = cartoes_ponto[pont][3].split(":");
                        int hora_saida = Integer.parseInt(tempo_saida[0]);
                        int minuto_saida = Integer.parseInt(tempo_saida[1]);

                        int tempo_trabalhado = hora_saida - hora_entrada;
                        if(minuto_saida < minuto_entrada) {
                            tempo_trabalhado += 1;
                        }
                        cartoes_ponto[pont][4] = "s";

                        horas_trabalhadas += tempo_trabalhado;
                    }
                }

                valor_calculado += (horas_trabalhadas * Float.parseFloat(funcs[id - 1][3]));

            } else if(funcs[id - 1][2].equals("2")) { // Assalariado

                valor_calculado += Float.parseFloat(funcs[id - 1][4]);

            } else { // Comissionado

                valor_calculado += Float.parseFloat(funcs[id - 1][4]);

                for(int vend = 0; vend < indice_vendas_realizadas; vend++) {
                    int id_vend = Integer.parseInt(vendas_realizadas[vend][0]);
                    if(id_vend == id && vendas_realizadas[vend][3].equals("n")) {
                        float comissao = Float.parseFloat(funcs[id - 1][5]);
                        float valor_venda = Float.parseFloat(vendas_realizadas[vend][1]);
                        valor_calculado += ((comissao / 100) * valor_venda);
                        vendas_realizadas[vend][3] = "s";
                    }
                }
            }

            System.out.printf("Valor a receber: R$%.2f\n", valor_calculado);

            funcs[id - 1][14] = funcs[id - 1][13];

            String data_final;
            int int_entrada = Integer.parseInt(funcs[id - 1][12]);
            String[] data = data_hoje.split("/");
            int dia_hoje = Integer.parseInt(data[0]);
            int mes_hoje = Integer.parseInt(data[1]);
            int ano_hoje = Integer.parseInt(data[2]);

            if(int_entrada == 1) {
                if(mes_hoje == 1 || mes_hoje == 3 || mes_hoje == 5 || mes_hoje == 7 || mes_hoje == 8 || mes_hoje == 10) {
                    if(dia_hoje + 7 > 31) {
                        dia_hoje = (dia_hoje + 7) - 31;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 7);
                    }
                } else if(mes_hoje == 4 || mes_hoje == 6 || mes_hoje == 9 || mes_hoje == 11) {
                    if(dia_hoje + 7 > 30) {
                        dia_hoje = (dia_hoje + 7) - 30;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 7);
                    }
                } else if(mes_hoje == 2) {
                    if(dia_hoje + 7 > 28) {
                        dia_hoje = (dia_hoje + 7) - 28;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 7);
                    }
                } else { // Mês == 12
                    if (dia_hoje + 7 > 31) {
                        dia_hoje = (dia_hoje + 7) - 31;
                        mes_hoje = 1;
                        ano_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 7);
                    }
                }
            } else if(int_entrada == 2) {
                if(mes_hoje == 1 || mes_hoje == 3 || mes_hoje == 5 || mes_hoje == 7 || mes_hoje == 8 || mes_hoje == 10) {
                    if(dia_hoje + 15 > 31) {
                        dia_hoje = (dia_hoje + 15) - 31;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 15);
                    }
                } else if(mes_hoje == 4 || mes_hoje == 6 || mes_hoje == 9 || mes_hoje == 11) {
                    if(dia_hoje + 15 > 30) {
                        dia_hoje = (dia_hoje + 15) - 30;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 15);
                    }
                } else if(mes_hoje == 2) {
                    if(dia_hoje + 15 > 28) {
                        dia_hoje = (dia_hoje + 15) - 28;
                        mes_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 15);
                    }
                } else { // Mês == 12
                    if (dia_hoje + 15 > 31) {
                        dia_hoje = (dia_hoje + 15) - 31;
                        mes_hoje = 1;
                        ano_hoje += 1;
                    } else {
                        dia_hoje = (dia_hoje + 15);
                    }
                }
            } else {
                if(mes_hoje == 12) {
                    mes_hoje = 1;
                    ano_hoje += 1;
                } else {
                    mes_hoje += 1;
                }
            }

            if(mes_hoje < 10) {
                if (dia_hoje < 10) {
                    data_final = "0" + dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
                } else {
                    data_final = dia_hoje + "/0" + mes_hoje + "/" + ano_hoje;
                }
            } else {
                if (dia_hoje < 10) {
                    data_final = "0" + dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
                } else {
                    data_final = dia_hoje + "/" + mes_hoje + "/" + ano_hoje;
                }
            }

            funcs[id - 1][13] = data_final;
        }

    } // Fim de Rodar Folha de Pagamentos


} // Fim da Classe Main
