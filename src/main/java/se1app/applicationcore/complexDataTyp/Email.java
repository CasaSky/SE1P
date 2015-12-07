package se1app.applicationcore.complexDataTyp;

//import com.google.common.base.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by talal on 04.12.15.
 */
@Embeddable
public final class Email /*implements UserType */{
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String emailAdress;

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public Email() {}
    private Email(String emailAdress) {
        // Preconditions.checkNotNull(emailAdress);
        //Preconditions.checkState(validate(emailAdress), "Invalid Email Adress!");
        this.emailAdress = emailAdress;
    }

    public static Email getEmail(String emailAdress) {
        return new Email(emailAdress);
    }
    public String getEmailAdress() {
        return emailAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;

        return emailAdress.equals(email.emailAdress);

    }

    /*public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y)
            return true;
        if (null == x || null == y)
            return false;
        return x.equals(y);
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {

    }

    public boolean isMutable() {
        return false;
    }

    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
            throws HibernateException, SQLException {
        String s = rs.getString(names[0]);
        if (s != null) {
            return new Email(s);
        }
        return null;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        if (null == value) {
            st.setNull(index, sqlTypes()[0]);
        } else {
            st.setString(index, ((Email) value).toString());
        }
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @SuppressWarnings("unchecked")
    public Class returnedClass() {
        return Email.class;
    }

    private static int[]    typs    = new int[] { Types.VARCHAR };

    public int[] sqlTypes() {
        return typs;
    }*/
}
