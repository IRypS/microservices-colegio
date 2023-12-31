USE [master]
GO
/****** Object:  Database [ColegioMicroserviceIRypS]    Script Date: 18/07/2023 22:00:04 ******/
CREATE DATABASE [ColegioMicroserviceIRypS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ColegioMicroserviceIRypS_Data', FILENAME = N'c:\dzsqls\ColegioMicroserviceIRypS.mdf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 22528KB )
 LOG ON 
( NAME = N'ColegioMicroserviceIRypS_Logs', FILENAME = N'c:\dzsqls\ColegioMicroserviceIRypS.ldf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 22528KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ColegioMicroserviceIRypS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ARITHABORT OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET  MULTI_USER 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET QUERY_STORE = OFF
GO
USE [ColegioMicroserviceIRypS]
GO
/****** Object:  User [IRypS_SQLLogin_2]    Script Date: 18/07/2023 22:00:06 ******/
CREATE USER [IRypS_SQLLogin_2] FOR LOGIN [IRypS_SQLLogin_2] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [IRypS_SQLLogin_2]
GO
/****** Object:  Schema [IRypS_SQLLogin_2]    Script Date: 18/07/2023 22:00:06 ******/
CREATE SCHEMA [IRypS_SQLLogin_2]
GO
/****** Object:  Table [dbo].[curso]    Script Date: 18/07/2023 22:00:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[curso](
	[id_curso] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[estado] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_curso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

-- Inserción de datos en la tabla curso
INSERT INTO curso (descripcion, estado)
VALUES ('Análisis y Diseño', 1),
       ('Base de Datos', 1),
       ('Lenguaje de Programación', 1),
       ('Inteligencia Artificial', 1);

USE [master]
GO
ALTER DATABASE [ColegioMicroserviceIRypS] SET  READ_WRITE 
GO
