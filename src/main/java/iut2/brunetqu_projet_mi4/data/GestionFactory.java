package iut2.brunetqu_projet_mi4.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class GestionFactory {

	//////////////////////////////////////////////////////////////persistence///////////////////////////////////////////////////////////////////////////////
	// Nom de l'unité de persistence
	// Permet le lien avec le fichier persistence.xml présent dans le dossier META-INF
	// Ce fichier contient les propriétés de connexion à la base de données
	private static final String PERSISTENCE_UNIT_NAME = "Projet_MI4_SQLITE";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL_DIST";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL_DIST2";

	// Factory pour la création d'EntityManager (gestion des transactions)
	public static EntityManagerFactory factory;


	// Creation de la factory
	public static void open() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// Fermeture de la factory
	public static void close() {
		factory.close();
	}

}