package com.practise;
class PersonNew {
	int id;
	String name;

	public String toString() {
		return "This method gives the String representation of an object. In the absence of this method the class name and the Hash code is used to represent the object";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonNew other = (PersonNew) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

public class ToStringAndEqualsMethod {

	public static void main(String[] args) {
		PersonNew person1 = new PersonNew();
		PersonNew person2 = new PersonNew();

		System.out.println(person1.toString());
		System.out.println(person1.hashCode());
		System.out.println(person2.hashCode());

		/*
		 * All objects have both identity (the object's location in memory) and
		 * state (the object's data). The == operator always compares identity.
		 * The default implementation of equals compares identity as well.
		 * Sometimes the default implementation of equals has the desired
		 * behaviour, but equals should usually compare state, not identity.
		 * 
		 * Another notable difference between == and equals method is that
		 * former is used to compare both primitive and objects while later is
		 * only used for objects comparison.
		 * 
		 * == compares object references, it checks to see if the two operands have the same object references or
		 * identity. if the two operands are object/class instances. Checks if
		 * the values of two operands are equal or not, if the two operands are
		 * of primitive datatypes.
		 */
		/*
		 * hashCode and equals are closely related:
		 * 
		 * if you override equals, you must override hashCode. hashCode must
		 * generate equal values for equal objects. equals and hashCode must
		 * depend on the same set of "significant" fields. You must use the same
		 * set of fields in both of these methods. You are not required to use
		 * all fields. For example, a calculated field that depends on others
		 * should very likely be omitted from equals and hashCode.
		 */
		System.out.println(person1 == person2);
		System.out.println(person1.equals(person2));

		int num1 = 3;
		int num2 = 3;
		System.out.println(num1 == num2);

		Double numb1 = 4.3;
		Double numb2 = 4.3;
		System.out.println(numb1 + " " + numb2);
		System.out.println(numb1 == numb2);
		System.out.println(numb1.equals(numb2));
		/*
		 * The Double class wraps a value of the primitive type double in an
		 * object. An object of type Double contains a single field whose type
		 * is double.
		 * 
		 * In addition, this class provides several methods for converting a
		 * double to a String and a String to a double, as well as other
		 * constants and methods useful when dealing with a double.
		 * 
		 * @since JDK1.0
		 */

		double numb3 = 4.3;
		double numb4 = 4.3;
		System.out.println(numb3 == numb4);

	}
}
