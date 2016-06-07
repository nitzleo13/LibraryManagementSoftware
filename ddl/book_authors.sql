USE [model]
GO

/****** Object:  Table [dbo].[book_authors]    Script Date: 11/3/2014 7:20:27 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[book_authors](
	[book_id] [varchar](50) NOT NULL,
	[author_name] [varchar](250) NOT NULL,
	[type] [int] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[book_authors]  WITH NOCHECK ADD  CONSTRAINT [FK_book_authors_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([book_id])
GO

ALTER TABLE [dbo].[book_authors] CHECK CONSTRAINT [FK_book_authors_Books]
GO


