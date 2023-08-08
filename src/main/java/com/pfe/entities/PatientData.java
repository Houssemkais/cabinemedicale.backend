package com.pfe.entities;



import lombok.Data;
import lombok.ToString;




@Data

@ToString
public class PatientData {
    private int Pregnancies;
    private int Glucose;
    private int BloodPressure;
    private int SkinThickness;
    private float BMI;
    private float DiabetesPedigreeFunction;
    private int Age;
}
