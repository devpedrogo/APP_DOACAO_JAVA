# Rede Solidária: Sistema de Doação e Reaproveitamento

## 📌 Sobre o Projeto
Este projeto é uma aplicação desenvolvida em **Java** com o objetivo de conectar doadores a instituições e pessoas em situação de vulnerabilidade. O foco é facilitar o fluxo de doações, garantindo rastreabilidade e priorização para quem mais precisa, combatendo o desperdício e promovendo a economia circular.

O projeto está alinhado com os **Objetivos de Desenvolvimento Sustentável (ODS)** da ONU:
* **ODS 1:** Erradicação da Pobreza
* **ODS 10:** Redução das Desigualdades
* **ODS 12:** Consumo e Produção Responsáveis

---

## 🚀 Status do Projeto: Checkpoint 1
Atualmente, o sistema encontra-se na fase de modelagem inicial e estrutura de dados, contendo:
- [x] Modelagem das classes principais (POO).
- [x] Diagrama de classes.
- [x] Sistema de menu via terminal.
- [x] Cadastro básico de Doadores, Beneficiários e Itens.

---

## 📐 Modelagem do Sistema
O sistema utiliza os conceitos de Herança e Encapsulamento para organizar os usuários e itens:

### Diagrama de Classes
![Diagrama de Classes](./docs/app_doacao.drawio%20(1).png) 
*(Dica: Suba a imagem para o seu repositório e coloque o caminho correto acima)*

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Versionamento:** Git & GitHub
* **Paradigma:** Programação Orientada a Objetos (POO)

---

## 📂 Estrutura de Pastas
```text
src/
 └── br/com/redesolidaria/
     ├── main/        # Classe de entrada e inicialização (Menu)
     ├── model/       # Classes de domínio (Doador, Beneficiário, Item)
     ├── repository/  # Gerenciamento de dados em memória
     └── util/        # Classes utilitárias e leitores