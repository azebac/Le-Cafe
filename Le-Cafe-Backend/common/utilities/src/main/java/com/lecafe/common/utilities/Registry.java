package com.lecafe.common.utilities;

import com.lecafe.common.exceptions.registry.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Registry
{
    public static final String AD_CONNECTION_TYPE = "ad.connectionType";
    public static final String AD_SERVER = "ad.server";
    public static final String AD_PORT = "ad.port";
    public static final String AD_ADMIN = "ad.admin";
    public static final String AD_ADMIN_PASS = "ad.adminPassword";
    public static final String AD_DOMAIN = "ad.domain";
    public static final String AD_ADMIN_GROUP = "ad.adminGroup";
    public static final String AD_USER_GROUP = "ad.userGroup";

    public static final String SMTP_PROTOCOL = "smtp.protocol";
    public static final String SMTP_HOST_NAME = "smtp.hostName";
    public static final String SMTP_NAME = "smtp.name";
    public static final String SMTP_PORT = "smtp.port";
    public static final String SMTP_POOL = "smtp.pool";
    public static final String SMTP_SENDER = "smtp.sender";
    public static final String SMTP_SENDER_PASSWORD = "smtp.senderPassword";

    public static final String SMTP_SOS_PROTOCOL = "smtp.sos.protocol";
    public static final String SMTP_SOS_HOST_NAME = "smtp.sos.hostName";
    public static final String SMTP_SOS_NAME = "smtp.sos.name";
    public static final String SMTP_SOS_PORT = "smtp.sos.port";
    public static final String SMTP_SOS_POOL = "smtp.sos.pool";
    public static final String SMTP_SOS_SENDER = "smtp.sos.sender";
    public static final String SMTP_SOS_SENDER_PASSWORD = "smtp.sos.senderPassword";

    public static final String DB_TYPE = "db.type";
    public static final String DB_UNIT = "db.unit";
    public static final String DB_JNDI = "db.jndi";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";

    public static final String MEDIC_DEFAULT_FEE = "medic.fee";
    public static final String MEDIC_DEFAULT_MEMBERSHIP = "medic.membership";
    public static final String MEDIC_LIMIT_QUERY = "medic.limitQuery";

    public static final String APPOINTMENT_NEXT_VALUE = "appointment.next.value";
    public static final String APPOINTMENT_NEXT_VALUE_MEDIC = "appointment.next.value.medic";
    public static final String APPOINTMENT_DURATION_VALUE = "appointment.duration.value";

    public static final String TEMPLATE_DIR = "template.dir";
    public static final String TEMPLATE_CHANGE_PASSWORD = "changePassword";
    public static final String TEMPLATE_RECOVERY_PASSWORD = "recoveryPassword";
    public static final String TEMPLATE_EMERGENCY_NOTIFICATION = "emergencyNotification";
    public static final String TEMPLATE_ADD_EMERGENCY_CONTACT = "addEmergencyContact";
    public static final String TEMPLATE_USER_REGISTRATION = "userRegistration";
    public static final String TEMPLATE_DOCTOR_REGISTRATION = "doctorRegistration";
    public static final String TEMPLATE_DOCTOR_REGISTRATION_REJECTED = "doctorRegistrationRejected";
    public static final String TEMPLATE_DOCTOR_REGISTRATION_APPROVED = "doctorRegistrationApproved";
    public static final String TEMPLATE_UPDATE_MEDICAL_HISTORY = "updateMedicalHistory";
    public static final String TEMPLATE_NEW_APPOINTMENT_PATIENT = "newAppointmentPatient";
    public static final String TEMPLATE_NEW_APPOINTMENT_DOCTOR = "newAppointmentDoctor";
    public static final String TEMPLATE_NEW_DEPENDENT_USER = "newDependentUser";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_MEDIC_BY_ADMIN = "cancelAppointmentMedicByAdmin";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_PATIENT_BY_ADMIN = "cancelAppointmentPatientByAdmin";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_BY_DOCTOR_DISMISSED = "cancelAppointmentByDoctorDismissed";
    public static final String TEMPLATE_RENEW_PIN = "renewPin";
    public static final String TEMPLATE_CANCELED_USER = "canceledUser";
    public static final String TEMPLATE_ACTIVATED_USER = "activatedUser";
    public static final String TEMPLATE_PRECANCELED_USER = "preCanceledUser";
    public static final String TEMPLATE_PRECANCELED_USER_PATIENT = "preCanceledUserPatient";
    public static final String TEMPLATE_APPOINTMENT_CONCLUDED = "appointmentConcluded";
    public static final String TEMPLATE_EMERGENCY_CONCLUDED = "emergencyConcluded";
    public static final String TEMPLATE_EMERGENCY_PAYMENT = "emergencyPayment";
    public static final String TEMPLATE_PATIENT_MISSED_APPOINTMENT_PATIENT = "patientMissedAppointmentPatient";
    public static final String TEMPLATE_PATIENT_MISSED_APPOINTMENT_DOCTOR = "patientMissedAppointmentDoctor";
    public static final String TEMPLATE_DOCTOR_MISSED_APPOINTMENT_PATIENT = "doctorMissedAppointmentPatient";
    public static final String TEMPLATE_DOCTOR_MISSED_APPOINTMENTE_DOCTOR = "doctorMissedAppointmentDoctor";
    public static final String TEMPLATE_DOCTOR_RESCHEDULE_APPOINTMENT_PATIENT = "doctorRescheduleAppointmentPatient";
    public static final String TEMPLATE_DOCTOR_RESCHEDULE_APPOINTMENT_DOCTOR = "doctorRescheduleAppointmentDoctor";
    public static final String TEMPLATE_PATIENT_RESCHEDULE_APPOINTMENT_PATIENT = "patientRescheduleAppointmentPatient";
    public static final String TEMPLATE_PATIENT_RESCHEDULE_APPOINTMENT_DOCTOR = "patientRescheduleAppointmentDoctor";
    public static final String TEMPLATE_ADMIN_RESCHEDULE_APPOINTMENT_DOCTOR = "adminRescheduleAppointmentDoctor";
    public static final String TEMPLATE_ADMIN_RESCHEDULE_APPOINTMENT_PATIENT = "adminRescheduleAppointmentPatient";
    public static final String TEMPLATE_VERIFIED_EMERGENCY_INFORMATION = "verifiedEmergencyInformation";
    public static final String TEMPLATE_UNCHECKED_EMERGENCY_INFORMATION = "uncheckedEmergencyInformation";
    public static final String TEMPLATE_PATIENT_FOLDER_PERMISSION_ADD = "patientFolderPermissionMedicAdd";
    public static final String TEMPLATE_PATIENT_FOLDER_PERMISSION_PENDING = "patientFolderPermissionPending";
    public static final String TEMPLATE_PATIENT_FOLDER_FILE_ADD = "patientFolderFileAddedByMedic";

    /**
     * Email Templates Appointment Cancelled
     */
    public static final String TEMPLATE_CANCEL_APPOINTMENT_DOCTOR_PATIENT = "cancelAppointmentByDoctorToPatient";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_DOCTOR_DOCTOR = "cancelAppointmentByDoctorToDoctor";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_PATIENT_PATIENT_ON_TIME =
            "cancelAppointmentOnTimeByPatientToPatient";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_PATIENT_DOCTOR_ON_TIME =
            "cancelAppointmentOnTimeByPatientToDoctor";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_PATIENT_PATIENT_OUT_OF_TIME =
            "cancelAppointmentOutOfTimeByPatientToPatient";
    public static final String TEMPLATE_CANCEL_APPOINTMENT_PATIENT_DOCTOR_OUT_OF_TIME =
            "cancelAppointmentOutOfTimeByPatientToDoctor";

    public static final String STRIPE_SECRET_KEY = "payment.stripe.key";
    public static final String PAYPAL_PUBLIC_KEY = "payment.paypal.publickey";
    public static final String PAYPAL_SECRET_KEY = "payment.paypal.secretkey";
    public static final String PAYPAL_MODE = "payment.paypal.mode";

    public static final String RESOURCE_DIR = "resource.dir";
    public static final String ATTACHMENT_DIR = "attachment.dir";
    public static final String PROFILE_PICTURE_DIR = "attachment.picture.original";
    public static final String PROFILE_PICTURE_MINI_DIR = "attachment.picture.mini";

    public static final String FIREBASE_URL = "firebase.url";
    public static final String FIREBASE_KEY = "firebase.key";

    public static final String PATIENTFOLDER_CAPACITY = "patientfolder.capacity";
    public static final String PATIENTFOLDER_BASEDIR = "patientfolder.basedir";

    static final String AES_SECRET = "aes.secret";

    static final String JWT_SECRET = "jwt.secret";
    static final String JWT_ISSUER = "jwt.issuer";
    static final String JWT_EXPIRATION = "jwt.expiration";

    static final String PASSWORD_CHARACTERS = "password.characters";
    static final String PASSWORD_LENGTH = "password.length";

    static final String PIN_CHARACTERS = "pin.characters";
    static final String PIN_LENGTH = "pin.length";

    private static final String PROPERTIES_FILE = "lecafe.properties";

    private static Logger _logger = LoggerFactory.getLogger( Registry.class );
    private static Registry _instance;

    private Properties _properties;

    public String getProperty( String key )
    {
        return _properties.getProperty( key );
    }

    private Registry()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a Registry.CTOR" );
        //endregion

        try
        {
            _properties = new Properties();
            _properties.load( getClass().getClassLoader().getResourceAsStream( PROPERTIES_FILE ) );
        }
        catch( IOException e )
        {
            throw new ConfigException( e, "Error reading properties from " + PROPERTIES_FILE );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de Registry.CTOR: Properties {}", _properties );
        //endregion
    }

    public static Registry getInstance()
    {
        if( _instance == null )
            _instance = new Registry();

        return _instance;
    }
}
