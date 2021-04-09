package com.safetynet.alerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

public class Data {
	private List<Person> persons;
	private List<FireStation> firestations;
	private List<MedicalRecord> medicalrecords;

	public Data() {
		super();
	}

	public Data(List<Person> persons, List<FireStation> firestations, List<MedicalRecord> medicalrecords) {
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * @return the firestations
	 */
	public List<FireStation> getFirestations() {
		return firestations;
	}

	/**
	 * @param firestations the firestations to set
	 */
	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	/**
	 * @return the medicalrecords
	 */
	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	/**
	 * @param medicalrecords the medicalrecords to set
	 */
	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	@Override
	public String toString() {
		return "Data [persons=" + persons + ", firestations=" + firestations + ", medicalrecords=" + medicalrecords
				+ "]";
	}

}
