export class Contact {
    constructor(
        public id: String | null = null,
        public academicTitle: string = '',
        public firstName: string = '',
        public lastName: string = '',
        public company: string = '',
        public department: string = '',
        public street: string = '',
        public zipCode: string = '',
        public cityTown: string = '',
        public nationalCode: string = '',
        public mail: string = '',
        public phone: string = '',
        public web: string = '',
    ) { }

    static fromJson(data: any) {
        return new Contact(
            data['id'],
            data['academicTitle'],
            data['firstName'],
            data['lastName'],
            data['company'],
            data['department'],
            data['street'],
            data['zipCode'],
            data['cityTown'],
            data['nationalCode'],
            data['mail'],
            data['phone'],
            data['web'],
        )
    }

    public fullName() {
        return [
            this.academicTitle,
            this.firstName,
            this.lastName,
        ].join(" ")
    }
}
