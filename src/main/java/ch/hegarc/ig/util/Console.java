package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import ch.hegarc.ig.util.StatsUtils.ExportPdf;
import ch.hegarc.ig.util.StatsUtils.ExportXls;
import ch.hegarc.ig.util.jackson.ImportJSON;
import ch.hegarc.ig.util.jackson.ExportJSON;
import ch.hegarc.ig.util.jaxb.ImportXML;
import org.apache.commons.cli.*;

import java.util.Scanner;

public class Console {

    private ProjetSet projets = new ProjetSet();

    final private String CMD_IMPORT = "import";
    final private String CMD_EXPORT = "export";
    final private String CMD_STATS = "stats";
    final private String CMD_EXIT = "exit";
    final private String CMD_ADDDONATEUR = "add";
    final private String CMD_REMOVEDONATEUR = "remove";

    final private Option OPT_FICHIER = new Option("f", "fichier", true, "nom du fichier");
    final private Option OPT_PROJET = new Option("p", "projet", true, "nom du projet");
    final private Option OPT_NOM = new Option("n", "nom", true, "nom du donateur");
    final private Option OPT_PRENOM = new Option("pr", "prenom", true, "prenom du donateur");
    final private Option OPT_SOMME = new Option("s", "somme", true, "somme donnee");

    /**
     * Démarre la commande
     */
    public void runCommand() {
        Scanner command = new Scanner(System.in);
        System.out.println("Entrer votre commande: ");

        boolean running = true;
        while (running) {
            String com = command.nextLine();
            String[] arguments = com.split(" ");
            CommandLine cmdLine = parseArguments(arguments);

            switch (cmdLine.getArgs()[0]) {

                case CMD_IMPORT:
                    if (cmdLine.hasOption(OPT_FICHIER.getOpt())) {
                        String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                        System.out.println("Import du fichier " + fileName);
                        try {
                            if (fileName.endsWith(".xml")) {
                                this.projets.addProjets(ImportXML.run(fileName));
                            } else if (fileName.endsWith(".json")) {
                                this.projets.addProjets(ImportJSON.run(fileName));
                            }
                            System.out.println(projets.toString());
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_EXPORT:
                    if (cmdLine.hasOption(OPT_FICHIER.getOpt()) && cmdLine.hasOption(OPT_PROJET.getOpt())) {
                        try {
                            String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                            String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                            if (!fileName.endsWith(".json")) {
                                System.out.println("Ce format de sortie n'est pas pris en charge, seul un fichier JSON peut être généré.");
                            } else if (projectName.equalsIgnoreCase("All")) {
                                System.out.println("Export de tous les projets.");
                                ExportJSON.run(this.projets.toList(), fileName);
                            } else if (projets.getProjectByName(projectName).getName() == null) {
                                System.out.println("Le projet " + projectName + " n'existe pas");
                            } else {
                                System.out.println("Export du projet " + fileName);
                                ExportJSON.run(projets.getProjectByName(projectName), fileName);

                                //TODO voir comment on veut utiliser ces fonctions
                                // idem Pour une liste de noms séparée par des virgules et reçu en argument, calculer le total des dons pour ces personnes, quelque soit le projet
                                System.out.println(projets.getMedianeEtMoyenne(projets.getProjectByName(projectName)));
                            }
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_STATS:
                    try {

                        if (projets.size() <= 0)
                            System.out.println("La base de données est vide et ne peut être exportée.");
                        //Test pour un export Excel
                        else if (cmdLine.hasOption(OPT_FICHIER.getOpt())) {
                            String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                            if (cmdLine.hasOption(OPT_PROJET.getOpt())) {
                                String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                                if (projets.getProjectByName(projectName).getName() == null) {
                                    System.out.println("Le projet " + projectName + " n'existe pas dans la base de données ! Aucun fichier n'a pas été généré.");
                                } else {
                                    ExportXls.statsProjet(projets.getProjectByName(projectName), fileName);
                                    System.out.println("Les statistiques du projet " + projectName + " ont été exportées dans le fichier : " + fileName);
                                }
                            } else if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
                                System.out.println("Ce format de sortie n'est pas pris en charge, seul un fichier XLSX ou XLS peut être généré.");
                            } else {
                                ExportXls.statsProjets(projets, fileName);
                                System.out.println("Les statistiques de tous les projets ont été exportées dans le fichier : " + fileName);
                            }
                        }
                        //Test pour Export PDF
                        else if (cmdLine.hasOption(OPT_PROJET.getOpt())) {
                            String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                            String fileName = ExportPdf.writePdf(projets.getProjectByName(projectName));
                            System.out.println("Les statistiques du projet " + projectName + " ont été exportées dans le fichier : " + fileName);
                        } else {
                            printAppHelp();
                        }
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                    break;

                case CMD_ADDDONATEUR:
                    if (cmdLine.hasOption(OPT_PROJET.getOpt()) && cmdLine.hasOption(OPT_NOM.getOpt()) && cmdLine.hasOption(OPT_PRENOM.getOpt()) && cmdLine.hasOption(OPT_SOMME.getOpt())) {
                        try {
                            String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                            String nom = cmdLine.getOptionValue(OPT_NOM.getOpt());
                            String prenom = cmdLine.getOptionValue(OPT_PRENOM.getOpt());
                            Long somme = Long.parseLong(cmdLine.getOptionValue(OPT_SOMME.getOpt()));

                            if (projets.getProjectByName(projectName).getName() == null) {
                                System.out.println("Projet inexistant ! Le don de " + nom + prenom + " n'a pas été pris en compte.");
                            } else {
                                Donateur donateur = new Donateur(nom, prenom, somme);
                                if (projets.getProjectByName(projectName).addDonateur(donateur)) {
                                    System.out.println(nom + " " + prenom + " vient de faire un don de " + somme + " au projet " + projectName);
                                } else {
                                    System.out.println(nom + " " + prenom + " a déjà fait un don pour le projet " + projectName);
                                }
                                System.out.println(projets.getProjectByName(projectName).toString());
                            }
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_REMOVEDONATEUR:
                    if (cmdLine.hasOption(OPT_PROJET.getOpt()) && cmdLine.hasOption(OPT_NOM.getOpt()) && cmdLine.hasOption(OPT_PRENOM.getOpt())) {
                        try {
                            Projet projet = projets.getProjectByName(cmdLine.getOptionValue(OPT_PROJET.getOpt()));
                            String nom = cmdLine.getOptionValue(OPT_NOM.getOpt());
                            String prenom = cmdLine.getOptionValue(OPT_PRENOM.getOpt());

                            if (projet.removeDonateur(nom, prenom)) {
                                System.out.println(nom + " " + prenom + " supprimé du projet " + projet.getName() + " avec succès!");
                            } else {
                                System.out.println(nom + " " + prenom + " n'a pas fait de don pour le projet " + projet.getName());
                            }
                            System.out.println(projets.getProjectByName(cmdLine.getOptionValue(OPT_PROJET.getOpt())).toString());
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_EXIT:
                    System.out.println("Fermeture !");
                    running = false;
                    break;

                default:
                    System.out.println("Commande non reconnue !");
                    printAppHelp();
                    break;
            }
        }
        command.close();
    }

    /**
     * Parses des arguments
     *
     * @param args application arguments
     * @return <code>CommandLine</code> which represents a list of application
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getAllOptions();
        CommandLine line = null;
        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);

        } catch (ParseException ex) {
            System.err.println("Erreur dans la lecture des arguments!");
            System.err.println(ex.toString());
            printAppHelp();
        }

        return line;
    }

    /**
     * Generates application command line options
     *
     * @return application <code>Options</code>
     */
    private Options getAllOptions() {

        Options options = new Options();
        options.addOption(OPT_FICHIER).addOption(OPT_PROJET).addOption(OPT_SOMME).addOption(OPT_PRENOM).addOption(OPT_NOM);
        return options;
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(CMD_IMPORT, new Options().addOption(OPT_FICHIER), true);
        formatter.printHelp(CMD_EXPORT, new Options().addOption(OPT_FICHIER).addOption(OPT_PROJET), true);
        formatter.printHelp(CMD_STATS, new Options().addOption(OPT_PROJET), true);
        formatter.printHelp(CMD_ADDDONATEUR, new Options().addOption(OPT_PROJET).addOption(OPT_NOM).addOption(OPT_PRENOM).addOption(OPT_SOMME), true);
        formatter.printHelp(CMD_REMOVEDONATEUR, new Options().addOption(OPT_PROJET).addOption(OPT_NOM).addOption(OPT_PRENOM), true);

        formatter.printHelp(CMD_EXIT, new Options());
    }
}
