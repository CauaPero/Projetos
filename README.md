# Projetos

Sistema de Reserva de Entradas de Teatro

Este projeto é um sistema em Java para gerenciamento de vendas de ingressos para espetáculos teatrais. Ele permite listar espetáculos, visualizar assentos disponíveis, selecionar um assento e gerar diferentes tipos de entradas (Inteira, Meia e Professor). O sistema também registra todas as entradas emitidas.

A aplicação foi desenvolvida utilizando princípios de Programação Orientada a Objetos, com foco em herança, polimorfismo e separação clara de responsabilidades.

A classe Main centraliza a lógica principal: armazena os espetáculos, controla qual espetáculo está selecionado, valida assentos e cria o tipo correto de entrada conforme a escolha do usuário. Para cada tipo (1, 2 ou 3), o sistema instancia a classe correspondente (EntradaInteira, EntradaMeia ou EntradaProfessor) e registra no histórico.

Cada espetáculo possui seu próprio mapa de assentos e métodos para verificar e ocupar lugares. O registro de entradas é feito pela classe Caminho, que guarda tudo o que foi emitido durante o uso do sistema.

O projeto demonstra domínio de conceitos essenciais de POO: classes abstratas, encapsulamento, validações simples, herança e polimorfismo aplicados a um cenário realista de bilheteria.