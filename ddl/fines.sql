USE [Bodhi_lbms]
GO

/****** Object:  Table [dbo].[fines]    Script Date: 11/3/2014 7:19:09 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[fines](
	[loan_id] [varchar](50) NOT NULL,
	[fine_amt] [decimal](18, 2) NOT NULL,
	[paid] [int] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[fines]  WITH CHECK ADD  CONSTRAINT [FK_fines_book_loans] FOREIGN KEY([loan_id])
REFERENCES [dbo].[book_loans] ([loan_id])
GO

ALTER TABLE [dbo].[fines] CHECK CONSTRAINT [FK_fines_book_loans]
GO


