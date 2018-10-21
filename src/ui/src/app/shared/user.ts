export class User {
    name: string;
    authenticated: boolean;
    authorities: Authority[]
}
export class Authority
{
    authority: string
}