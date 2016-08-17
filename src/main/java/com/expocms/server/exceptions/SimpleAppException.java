package com.expocms.server.exceptions;

public class SimpleAppException extends Exception
{
	private static final long serialVersionUID = -6813227040786629130L;
	private Type type;

    public SimpleAppException( Type type )
    {
        super();
        this.type = type;
    }
    public SimpleAppException( Throwable t, Type type )
    {
        super( t );
        this.type = type;
    }
    public String toString() {
        return super.toString() + "<" + getErrorType().getErrorCode() + ">";
    }

    public Type getErrorType()
    {
        return type;
    }

    public enum Type
    {
        // 系统错误
        SYSTEM_ERROR( "99999" ),
        // 用户认证错误
        USER_AUTH( "03003" );
        private String errorCode;
        Type( String errorCode )
        {
            this.errorCode = errorCode;
        }
        public String getErrorCode()
        {
            return this.errorCode;
        }
    }
}
